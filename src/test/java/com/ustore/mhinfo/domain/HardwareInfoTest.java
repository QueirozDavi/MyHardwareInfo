package com.ustore.mhinfo.domain;

import com.ustore.mhinfo.config.MockFactory;
import com.ustore.mhinfo.domain.builder.HardwareSummaryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class HardwareInfoTest {

    private Disk disk;
    private Memory memory;
    private List<CpuInfo> cpuInfos;
    private final double delta = 0.01;

    @Before
    public void setUp() {
        this.disk = new MockFactory().getDisks().get(0);
        this.cpuInfos = new MockFactory().getCpuInfos();
        this.memory = new MockFactory().getMemories().get(0);
    }

    @Test
    public void shouldCreateDiskWithAllParameters() {
        HardwareInfo hardwareInfo = new HardwareSummaryBuilder()
                .withCupInfos(cpuInfos)
                .withDisk(disk)
                .withMemory(memory)
                .build();

        assertTrue(Objects.nonNull(hardwareInfo));
        assertDisk(hardwareInfo.getDisk());
        assertMemory(hardwareInfo.getMemory());
        assertCpuInfos(hardwareInfo.getCpuInfos());

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateHardwareSummaryWithOuAllParameters() {
        HardwareInfo hardwareInfo = new HardwareSummaryBuilder().build();
    }

    @Test
    public void shouldCreateHardwareSummaryWithDiskOnly() {
        HardwareInfo hardwareInfo = new HardwareSummaryBuilder()
                .withDisk(disk)
                .build();

        assertDisk(hardwareInfo.getDisk());
    }

    @Test
    public void shouldCreateHardwareSummaryWithMemoryOnly() {
        HardwareInfo hardwareInfo = new HardwareSummaryBuilder()
                .withMemory(memory)
                .build();

        assertMemory(hardwareInfo.getMemory());
    }

    @Test
    public void shouldCreateHardwareSummaryWithCpuInfosOnly() {
        HardwareInfo hardwareInfo = new HardwareSummaryBuilder()
                .withCupInfos(cpuInfos)
                .build();

        assertCpuInfos(hardwareInfo.getCpuInfos());
    }

    private void assertMemory(Memory memory) {
        assertNotNull(memory);
        assertEquals(memory.getUsedHeapMemory(), memory.getUsedHeapMemory(), delta);
        assertEquals(memory.getMaxHeapMemory(), memory.getMaxHeapMemory(), delta);
        assertEquals(memory.getCommittedMemory(), memory.getCommittedMemory(), delta);
        assertEquals(memory.getInitialMemory(), memory.getInitialMemory(), delta);
    }

    private void assertDisk(Disk disk) {
        assertNotNull(disk);
        assertEquals(disk.getUsableSpace(), this.disk.getUsableSpace());
        assertEquals(disk.getFreeSpace(), this.disk.getFreeSpace());
        assertEquals(disk.getTotalDiskSpace(), this.disk.getTotalDiskSpace());
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
