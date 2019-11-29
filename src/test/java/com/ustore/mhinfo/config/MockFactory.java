package com.ustore.mhinfo.config;

import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.Memory;
import com.ustore.mhinfo.domain.builder.CpuInfoBuilder;
import com.ustore.mhinfo.domain.builder.DiskBuilder;
import com.ustore.mhinfo.domain.builder.MemoryBuilder;


import java.util.Arrays;
import java.util.List;

public class MockFactory {

    public List<CpuInfo> getCpuInfos() {

        CpuInfo cpu1 = new CpuInfoBuilder()
                .withCpuTime("50001")
                .withThreadState("NEW")
                .withThreadName("CPU Thread Factory 1")
                .build();

        CpuInfo cpu2 = new CpuInfoBuilder()
                .withCpuTime("50002")
                .withThreadState("WAITING")
                .withThreadName("CPU Thread Factory 2")
                .build();

        CpuInfo cpu3 = new CpuInfoBuilder()
                .withCpuTime("50003")
                .withThreadState("RUNNABLE")
                .withThreadName("CPU Thread Factory 3")
                .build();

        CpuInfo cpu4 = new CpuInfoBuilder()
                .withCpuTime("50004")
                .withThreadState("TIMED_WAITING")
                .withThreadName("CPU Thread Factory 4")
                .build();

        return Arrays.asList(cpu1, cpu2, cpu3, cpu4);
    }

    public List<Disk> getDisks() {
        Disk d1 = new DiskBuilder()
                .withFreeSpace(10001)
                .withUsableSpace(10001)
                .withTotalDiskSpace(1001)
                .build();

        Disk d2 = new DiskBuilder()
                .withFreeSpace(20002)
                .withUsableSpace(20002)
                .withTotalDiskSpace(2002)
                .build();

        Disk d3 = new DiskBuilder()
                .withFreeSpace(30003)
                .withUsableSpace(30003)
                .withTotalDiskSpace(3003)
                .build();

        return Arrays.asList(d1, d2, d3);
    }

    public List<Memory> getMemories() {
        Memory m1 = new MemoryBuilder()
                .withCommittedMemory(1001.1)
                .withInitialMemory(1001.1)
                .withMaxHeapMemory(1001.1)
                .withUsedHeapMemory(1001.1)
                .build();

        Memory m2 = new MemoryBuilder()
                .withCommittedMemory(2002.2)
                .withInitialMemory(2001.2)
                .withMaxHeapMemory(2001.2)
                .withUsedHeapMemory(2001.2)
                .build();

        Memory m3 = new MemoryBuilder()
                .withCommittedMemory(3003.3)
                .withInitialMemory(3003.3)
                .withMaxHeapMemory(3003.3)
                .withUsedHeapMemory(3003.3)
                .build();

        return Arrays.asList(m1, m2, m3);
    }
}
