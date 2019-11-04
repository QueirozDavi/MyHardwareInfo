package com.ustore.mhinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HardwareSummary {

    private Disk disk;
    private Memory memory;
    private List<CpuInfo> cpuInfos;

}
