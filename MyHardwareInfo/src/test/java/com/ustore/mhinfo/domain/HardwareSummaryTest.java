package com.ustore.mhinfo.domain;

import com.ustore.mhinfo.config.MockFactory;
import com.ustore.mhinfo.domain.builder.HardwareSummaryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class HardwareSummaryTest {

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
        HardwareSummary hardwareSummary = new HardwareSummaryBuilder()
                .withCupInfos(cpuInfos)
                .withDisk(disk)
                .withMemory(memory)
                .build();

        assertTrue(Objects.nonNull(hardwareSummary));
        getAssertsDisk(hardwareSummary);
        getAssertsMemory(hardwareSummary);
        getAssertsCpuInfos(hardwareSummary);

    }

    @Test( expected = NullPointerException.class)
    public void shouldNotCreateNotHardwareSummaryWithOuAllParameters() {
        HardwareSummary hardwareSummary = new HardwareSummaryBuilder().build();
    }

    @Test
    public void shouldCreateHardwareSummaryWithDiskOnly() {
        HardwareSummary hardwareSummary = new HardwareSummaryBuilder()
                .withDisk(disk)
                .build();

        getAssertsDisk(hardwareSummary);
    }

    @Test
    public void shouldCreateHardwareSummaryWithMemoryOnly() {
        HardwareSummary hardwareSummary = new HardwareSummaryBuilder()
                .withMemory(memory)
                .build();

        getAssertsMemory(hardwareSummary);
    }

    @Test
    public void shouldCreateHardwareSummaryWithCpuInfosOnly() {
        HardwareSummary hardwareSummary = new HardwareSummaryBuilder()
                .withCupInfos(cpuInfos)
                .build();

        getAssertsCpuInfos(hardwareSummary);
    }

    private void getAssertsMemory(HardwareSummary hardwareSummary) {
        assertNotNull(hardwareSummary.getMemory());
        assertEquals(hardwareSummary.getMemory().getUsedHeapMemory(), memory.getUsedHeapMemory(), delta);
        assertEquals(hardwareSummary.getMemory().getMaxHeapMemory(), memory.getMaxHeapMemory(), delta);
        assertEquals(hardwareSummary.getMemory().getCommittedMemory(), memory.getCommittedMemory(), delta);
        assertEquals(hardwareSummary.getMemory().getInitialMemory(), memory.getInitialMemory(), delta);
    }

    private void getAssertsDisk(HardwareSummary hardwareSummary) {
        assertNotNull(hardwareSummary.getDisk());
        assertEquals(hardwareSummary.getDisk().getUsableSpace(), disk.getUsableSpace());
        assertEquals(hardwareSummary.getDisk().getFreeSpace(), disk.getFreeSpace());
        assertEquals(hardwareSummary.getDisk().getTotalDiskSpace(), disk.getTotalDiskSpace());
    }

    private void getAssertsCpuInfos(HardwareSummary hardwareSummary) {
        assertFalse(CollectionUtils.isEmpty(hardwareSummary.getCpuInfos()));
        assertTrue(hardwareSummary.getCpuInfos().size() > 0);
        assertTrue(hardwareSummary.getCpuInfos().size() == 4);
        assertEquals(hardwareSummary.getCpuInfos().get(0).getCpuTime(), cpuInfos.get(0).getCpuTime());
        assertEquals(hardwareSummary.getCpuInfos().get(1).getCpuTime(), cpuInfos.get(1).getCpuTime());
        assertEquals(hardwareSummary.getCpuInfos().get(2).getCpuTime(), cpuInfos.get(2).getCpuTime());
        assertEquals(hardwareSummary.getCpuInfos().get(3).getCpuTime(), cpuInfos.get(3).getCpuTime());
        assertEquals(hardwareSummary.getCpuInfos().get(0).getThreadName(), cpuInfos.get(0).getThreadName());
        assertEquals(hardwareSummary.getCpuInfos().get(1).getThreadName(), cpuInfos.get(1).getThreadName());
        assertEquals(hardwareSummary.getCpuInfos().get(2).getThreadName(), cpuInfos.get(2).getThreadName());
        assertEquals(hardwareSummary.getCpuInfos().get(3).getThreadName(), cpuInfos.get(3).getThreadName());
        assertEquals(hardwareSummary.getCpuInfos().get(0).getCpuTime(), cpuInfos.get(0).getCpuTime());
        assertEquals(hardwareSummary.getCpuInfos().get(1).getCpuTime(), cpuInfos.get(1).getCpuTime());
        assertEquals(hardwareSummary.getCpuInfos().get(2).getCpuTime(), cpuInfos.get(2).getCpuTime());
        assertEquals(hardwareSummary.getCpuInfos().get(3).getCpuTime(), cpuInfos.get(3).getCpuTime());
    }

}
