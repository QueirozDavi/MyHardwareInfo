package com.ustore.mhinfo.service;

import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.HardwareSummary;
import com.ustore.mhinfo.domain.Memory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public HardwareSummary getHardwareSummary() {
        this.file = new File(operationalSystem);
        return new HardwareSummary(getDiskInformation(), getMemoryInformation(), getCpuListInformations());
    }

    private List<CpuInfo> getCpuListInformations() {

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


}
