package com.ustore.mhinfo.domain;

import lombok.Data;

@Data
public class CpuInfo {

    private String threadName;
    private String threadState;
    private String cpuTime;

}
