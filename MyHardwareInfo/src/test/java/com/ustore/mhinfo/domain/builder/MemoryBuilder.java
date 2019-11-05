package com.ustore.mhinfo.domain.builder;

import com.ustore.mhinfo.domain.Memory;
import com.ustore.mhinfo.domain.Memory;

public class MemoryBuilder {

    private Memory memory;

    public MemoryBuilder() {
        memory = new Memory();
    }

    public Memory build() {
        if (memory.getUsedHeapMemory() <= 0 &&
                memory.getInitialMemory() <= 0 &&
                memory.getCommittedMemory() <= 0 &&
                memory.getMaxHeapMemory() <= 0)
            throw new NullPointerException("At least one parameter must be set.");

        return memory;
    }

    public MemoryBuilder withInitialMemory(double initialMemory) {
        memory.setInitialMemory(initialMemory);
        return this;
    }

    public MemoryBuilder withUsedHeapMemory(double usedHeapMemory) {
        memory.setUsedHeapMemory(usedHeapMemory);
        return this;
    }

    public MemoryBuilder withCommittedMemory(double committedMemory) {
        memory.setCommittedMemory(committedMemory);
        return this;
    }

    public MemoryBuilder withMaxHeapMemory(double maxHeapMemory) {
        memory.setMaxHeapMemory(maxHeapMemory);
        return this;
    }

}
