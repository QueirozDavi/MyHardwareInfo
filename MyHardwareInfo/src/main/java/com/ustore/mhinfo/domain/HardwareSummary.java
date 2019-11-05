package com.ustore.mhinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareSummary {

    private Disk disk;
    private Memory memory;
    private List<CpuInfo> cpuInfos;

}
