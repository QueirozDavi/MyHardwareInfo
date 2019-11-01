package com.ustore.mhinfo.service;

import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.HardwareSummary;
import com.ustore.mhinfo.domain.Memory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@Service
public class ResourceCollectorService {

    private File file;
    private MemoryMXBean memoryMXBean;
    @Value("${system.windows}")
    private String operationalSystem;

    @Autowired
    public ResourceCollectorService() {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
        this.file = new File(operationalSystem);
    }

    public HardwareSummary getHardwareSummary() {
        return new HardwareSummary(getDiskInformation(), getMemoryInformation());
    }

    private Disk getDiskInformation() {

        Disk disk = new Disk();
        disk.setTotalDiskSpace(file.getTotalSpace());
        disk.setFreeSpace(file.getFreeSpace());
        disk.setUsableSpace(file.getUsableSpace());

        return disk;
    }

    private Memory getMemoryInformation() {

        Memory memory = new Memory();
        memory.setInitialMemory(memoryMXBean.getHeapMemoryUsage().getInit());
        memory.setCommittedMemory(memoryMXBean.getHeapMemoryUsage().getCommitted());
        memory.setMaxHeapMemory(memoryMXBean.getHeapMemoryUsage().getMax());
        memory.setUsedHeapMemory(memoryMXBean.getHeapMemoryUsage().getUsed());

        return memory;
    }


}
