package com.ustore.mhinfo.domain;

import lombok.Data;

@Data
public class Memory {

    private double initialMemory;
    private double usedHeapMemory;
    private double maxHeapMemory;
    private double committedMemory;
}
