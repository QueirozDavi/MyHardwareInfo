package com.ustore.mhinfo.domain.dto;

import lombok.Data;

@Data
public class HardwareSummaryDTO {

    private String totalDiskSpace;
    private String freeDiskSpace;
    private String usableDiskSpace;
    private String initialMemory;
    private String usedHeapMemory;
    private String maxHeapMemory;
    private String commitedMemory;
}
