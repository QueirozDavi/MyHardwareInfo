package com.ustore.mhinfo.domain.dto;

import com.ustore.mhinfo.config.MockFactory;
import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.builder.HardwareSummaryDTOBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.*;

public class HardwareInfoDTOTest {

    private String totalDiskSpace;
    private String freeDiskSpace;
    private String usableDiskSpace;
    private String initialMemory;
    private String usedHeapMemory;
    private String maxHeapMemory;
    private String committedHeapMemory;
    private List<CpuInfo> cpuInfos;

    @Before
    public void setUp() {
        this.totalDiskSpace = "Total space: 222,34 GB";
        this.freeDiskSpace = "Free space: 147,45 GB";
        this.usableDiskSpace = "Usable space: 147,45 GB";
        this.initialMemory = "Initial memory: 0,25 GB";
        this.usedHeapMemory = "Used heap memory: 0,03 GB";
        this.maxHeapMemory = "Max heap memory: 3,51 GB";
        this.committedHeapMemory = "Committed memory: 0,29 GB";
        this.cpuInfos = new MockFactory().getCpuInfos();
    }

    @Test
    public void shouldCreateHardwareSummaryDTOWithAllParameters() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder()
                .withCommittedHeapMemory(committedHeapMemory)
                .withFreeDiskSpace(freeDiskSpace)
                .withInitialMemory(initialMemory)
                .withUsedHeapMemory(usedHeapMemory)
                .withMaxHeapMemory(maxHeapMemory)
                .withTotalDiskSpace(totalDiskSpace)
                .withUsableDiskSpace(usableDiskSpace)
                .withCpuInfos(cpuInfos)
                .build();

        assertNotNull(hardwareSummaryDTO);
        assertEquals(hardwareSummaryDTO.getCommittedHeapMemory(), this.committedHeapMemory);
        assertEquals(hardwareSummaryDTO.getInitialMemory(), this.initialMemory);
        assertEquals(hardwareSummaryDTO.getMaxHeapMemory(), this.maxHeapMemory);
        assertEquals(hardwareSummaryDTO.getFreeDiskSpace(), this.freeDiskSpace);
        assertEquals(hardwareSummaryDTO.getTotalDiskSpace(), this.totalDiskSpace);
        assertEquals(hardwareSummaryDTO.getUsableDiskSpace(), this.usableDiskSpace);
        assertCpuInfos(hardwareSummaryDTO.getCpuInfos());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateHardwareSummaryWithOuAllParameters() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder().build();
    }

    @Test
    public void shouldCreateHardwareSummaryWithCommittedHeapMemoryOnly() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder()
                .withCommittedHeapMemory(committedHeapMemory)
                .build();

        assertNotNull(hardwareSummaryDTO);
        assertEquals(hardwareSummaryDTO.getCommittedHeapMemory(), this.committedHeapMemory);
    }

    @Test
    public void shouldCreateHardwareSummaryWithInitialMemoryOnly() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder()
                .withInitialMemory(initialMemory)
                .build();

        assertNotNull(hardwareSummaryDTO);
        assertEquals(hardwareSummaryDTO.getInitialMemory(), this.initialMemory);
    }

    @Test
    public void shouldCreateHardwareSummaryWithMaxHeapMemoryOnly() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder()
                .withMaxHeapMemory(maxHeapMemory)
                .build();

        assertNotNull(hardwareSummaryDTO);
        assertEquals(hardwareSummaryDTO.getMaxHeapMemory(), this.maxHeapMemory);
    }

    @Test
    public void shouldCreateHardwareSummaryWithFreeDiskSpaceOnly() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder()
                .withFreeDiskSpace(freeDiskSpace)
                .build();

        assertNotNull(hardwareSummaryDTO);
        assertEquals(hardwareSummaryDTO.getFreeDiskSpace(), this.freeDiskSpace);
    }

    @Test
    public void shouldCreateHardwareSummaryWithTotalDiskSpaceOnly() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder()
                .withTotalDiskSpace(totalDiskSpace)
                .build();

        assertNotNull(hardwareSummaryDTO);
        assertEquals(hardwareSummaryDTO.getTotalDiskSpace(), this.totalDiskSpace);
    }

    @Test
    public void shouldCreateHardwareSummaryWithUsableDiskSpaceOnly() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder()
                .withUsableDiskSpace(usableDiskSpace)
                .build();

        assertNotNull(hardwareSummaryDTO);
        assertEquals(hardwareSummaryDTO.getUsableDiskSpace(), this.usableDiskSpace);
    }

    @Test
    public void shouldCreateHardwareSummaryWithCpuInfosOnly() {
        HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTOBuilder()
                .withCpuInfos(cpuInfos)
                .build();

        assertCpuInfos(hardwareSummaryDTO.getCpuInfos());
    }

    private void assertCpuInfos(List<CpuInfo> cpuInfos) {
        assertFalse(CollectionUtils.isEmpty(cpuInfos));
        assertTrue(cpuInfos.size() > 0);
        assertEquals(cpuInfos.size(), 4);

        for (CpuInfo cpuInfo : cpuInfos) {
            assertEquals(cpuInfo.getThreadState(), this.cpuInfos.get(this.cpuInfos.indexOf(cpuInfo)).getThreadState());
            assertEquals(cpuInfo.getThreadName(), this.cpuInfos.get(this.cpuInfos.indexOf(cpuInfo)).getThreadName());
            assertEquals(cpuInfo.getCpuTime(), this.cpuInfos.get(this.cpuInfos.indexOf(cpuInfo)).getCpuTime());
        }
    }
}
