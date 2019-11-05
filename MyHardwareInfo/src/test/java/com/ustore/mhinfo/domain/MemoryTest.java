package com.ustore.mhinfo.domain;


import com.ustore.mhinfo.domain.builder.MemoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemoryTest {

    private double initialMemory;
    private double usedHeapMemory;
    private double maxHeapMemory;
    private double committedMemory;
    private final long emptyParameter = 0;
    private final double delta = 0.01;

    @Before
    public void setUp() {
        this.initialMemory = 1001.1;
        this.usedHeapMemory = 1001.2;
        this.maxHeapMemory = 1003.3;
        this.committedMemory = 1004.4;
    }

    @Test
    public void shouldCreateDiskWithAllParameters() {
        Memory memory = new MemoryBuilder()
                .withCommittedMemory(committedMemory)
                .withInitialMemory(initialMemory)
                .withUsedHeapMemory(usedHeapMemory)
                .withMaxHeapMemory(maxHeapMemory)
                .build();

        assertTrue(Objects.nonNull(memory));
        getAssertsCommittedMemory(memory);
        getAssertsInitialMemory(memory);
        getAssertsUsedHeapMemory(memory);
        getAssertsMaxHeapMemory(memory);
    }

    @Test( expected = NullPointerException.class)
    public void shouldNotCreateNotDiskWithOuAllParameters() {
        Memory memory = new MemoryBuilder().build();
    }

    @Test
    public void shouldCreateMemoryWithInitialMemoryOnly() {
        Memory memory = new MemoryBuilder()
                .withInitialMemory(initialMemory)
                .build();

        assertTrue(Objects.nonNull(memory));
        getAssertsInitialMemory(memory);
    }

    @Test
    public void shouldCreateMemoryWithUsedHeapMemoryOnly() {
        Memory memory = new MemoryBuilder()
                .withUsedHeapMemory(usedHeapMemory)
                .build();

        assertTrue(Objects.nonNull(memory));
        getAssertsUsedHeapMemory(memory);
    }

    @Test
    public void shouldCreateMemoryWithMaxHeapMemoryOnly() {
        Memory memory = new MemoryBuilder()
                .withMaxHeapMemory(maxHeapMemory)
                .build();

        assertTrue(Objects.nonNull(memory));
        getAssertsMaxHeapMemory(memory);
    }

    @Test
    public void shouldCreateMemoryWithCommitedMemoryOnly() {
        Memory memory = new MemoryBuilder()
                .withCommittedMemory(committedMemory)
                .build();

        assertTrue(Objects.nonNull(memory));
        getAssertsCommittedMemory(memory);
    }

    private void getAssertsCommittedMemory(Memory memory) {
        assertTrue(memory.getCommittedMemory() > emptyParameter);
        assertEquals(memory.getCommittedMemory(), committedMemory, delta);
    }

    private void getAssertsMaxHeapMemory(Memory memory) {
        assertTrue(memory.getMaxHeapMemory() > emptyParameter);
        assertEquals(memory.getMaxHeapMemory(), maxHeapMemory, delta);
    }

    private void getAssertsUsedHeapMemory(Memory memory) {
        assertTrue(memory.getUsedHeapMemory() > emptyParameter);
        assertEquals(memory.getUsedHeapMemory(), usedHeapMemory, delta);
    }

    private void getAssertsInitialMemory(Memory memory) {
        assertTrue(memory.getInitialMemory() > emptyParameter);
        assertEquals(memory.getInitialMemory(), initialMemory, delta);
    }
}
