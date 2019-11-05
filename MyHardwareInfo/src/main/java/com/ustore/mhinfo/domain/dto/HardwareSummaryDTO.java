package com.ustore.mhinfo.domain.dto;

import com.ustore.mhinfo.domain.CpuInfo;
import lombok.Data;

import java.util.List;

@Data
public class HardwareSummaryDTO {

    private String totalDiskSpace;
    private String freeDiskSpace;
    private String usableDiskSpace;
    private String initialMemory;
    private String usedHeapMemory;
    private String maxHeapMemory;
    private String committedHeapMemory;
    private List<CpuInfo> cpuInfos;

}
