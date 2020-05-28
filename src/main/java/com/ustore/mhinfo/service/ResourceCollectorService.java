package com.ustore.mhinfo.service;

import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.HardwareInfo;
import com.ustore.mhinfo.domain.Memory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.management.Attribute;
import javax.management.InstanceNotFoundException;
import javax.management.ReflectionException;
import java.io.File;
import java.lang.management.*;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ResourceCollectorService {

    private File file;
    private final MemoryMXBean memoryMXBean;
    @Value("${system.windows}")
    private String operationalSystem;
    private final ThreadMXBean threadMXBean;
    private final ModelMapper modelMapper;

    @Autowired
    public ResourceCollectorService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
        this.threadMXBean = ManagementFactory.getThreadMXBean();
    }

    public HardwareInfo getHardwareSummary() {
        this.file = new File(getWindowsPathValue());
        HardwareInfo hardwareInfo = new HardwareInfo(getDiskInformation(), getMemoryInformation(),
                getCpuListInformations());

        String[] attr = {"TotalPhysicalMemorySize", "FreePhysicalMemorySize"};
        OperatingSystemMXBean op = ManagementFactory.getOperatingSystemMXBean();

        List<Attribute> al;

        try {
            al = ManagementFactory.getPlatformMBeanServer()
                    .getAttributes(op.getObjectName(), attr).asList();
        } catch (InstanceNotFoundException | ReflectionException ex) {
            al = Collections.emptyList();
        }
        for (Attribute a : al) {

            if (a.getName().equals("TotalPhysicalMemorySize")) {
                hardwareInfo.getMemory().setTotalOpMemory((Long.parseLong(a.getValue().toString()) / 1000.0));
                ConvertToReadebleDouble(hardwareInfo);
            }
        }

        return hardwareInfo;
    }

    private void ConvertToReadebleDouble(HardwareInfo hardwareInfo) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        hardwareInfo.getMemory().setTotalOpMemory(Double.parseDouble(decimalFormat.format(hardwareInfo
                .getMemory().getTotalOpMemory() / 1048576D).replace(",", ".")));
    }

    public List<CpuInfo> getCpuListInformations() {

        List<CpuInfo> cpuInfos = new ArrayList<>();
        for (Long threadId : threadMXBean.getAllThreadIds()) {
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
            CpuInfo cpuInfo = modelMapper.map(threadInfo, CpuInfo.class);
            cpuInfo.setCpuTime(String.format("CPU time: %s ns", threadMXBean.getThreadCpuTime(threadId)));
            cpuInfos.add(cpuInfo);

        }

        return cpuInfos;
    }

    private Disk getDiskInformation() {
        return new Disk(file);
    }

    private Memory getMemoryInformation() {
        return new Memory(memoryMXBean);
    }

    public String getWindowsPathValue() {
        return operationalSystem;
    }
}
