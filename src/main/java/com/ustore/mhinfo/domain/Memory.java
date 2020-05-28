package com.ustore.mhinfo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.management.MemoryMXBean;

@Data
@NoArgsConstructor
public class Memory {

    private double initialMemory;
    private double usedHeapMemory;
    private double maxHeapMemory;
    private double committedMemory;
    private double totalOpMemory;

    public Memory(MemoryMXBean memoryMXBean) {
        this.setInitialMemory(memoryMXBean.getHeapMemoryUsage().getInit());
        this.setCommittedMemory(memoryMXBean.getHeapMemoryUsage().getCommitted());
        this.setMaxHeapMemory(memoryMXBean.getHeapMemoryUsage().getMax());
        this.setUsedHeapMemory(memoryMXBean.getHeapMemoryUsage().getUsed());
    }
}
