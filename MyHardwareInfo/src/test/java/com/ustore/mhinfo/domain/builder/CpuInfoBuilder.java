package com.ustore.mhinfo.domain.builder;

import com.ustore.mhinfo.domain.CpuInfo;

import java.util.Objects;

public class CpuInfoBuilder {

    private CpuInfo cpuInfo;

    public CpuInfoBuilder() {
        cpuInfo = new CpuInfo();
    }

    public CpuInfo build() {
        if (Objects.isNull(cpuInfo.getCpuTime()) &&
                Objects.isNull(cpuInfo.getThreadName()) &&
                Objects.isNull(cpuInfo.getThreadState()))
            throw new NullPointerException("At least one parameter must be set.");

        return cpuInfo;
    }

    public CpuInfoBuilder withThreadName(String threadName) {
        cpuInfo.setThreadName(threadName);
        return this;
    }

    public CpuInfoBuilder withThreadState(String threadStatate) {
        cpuInfo.setThreadState(threadStatate);
        return this;
    }

    public CpuInfoBuilder withCpuTime(String cpuTime) {
        cpuInfo.setCpuTime(cpuTime);
        return this;
    }

}
