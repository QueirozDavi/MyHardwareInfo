package com.ustore.mhinfo.domain.builder;

import com.ustore.mhinfo.config.MockFactory;
import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.HardwareInfo;
import com.ustore.mhinfo.domain.Memory;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Data
public class HardwareSummaryBuilder {

    private HardwareInfo hardwareInfo;

    public HardwareSummaryBuilder() {
        hardwareInfo = new HardwareInfo();
    }

    public HardwareInfo build() {
        if (Objects.isNull(hardwareInfo.getDisk()) &&
                Objects.isNull(hardwareInfo.getMemory()) &&
                CollectionUtils.isEmpty(hardwareInfo.getCpuInfos()))
            throw new IllegalArgumentException("At least one parameter must be set.");

        return hardwareInfo;
    }

    public HardwareSummaryBuilder withCupInfos(List<CpuInfo> cupInfos) {

        if (CollectionUtils.isEmpty(cupInfos))
            hardwareInfo.setCpuInfos(cupInfos);
        else
            hardwareInfo.setCpuInfos(new MockFactory().getCpuInfos());

        return this;
    }

    public HardwareSummaryBuilder withDisk(Disk disk) {

        if(Objects.nonNull(disk))
            hardwareInfo.setDisk(disk);
        else
            hardwareInfo.setDisk(new MockFactory().getDisks().get(0));

        return this;
    }

    public HardwareSummaryBuilder withMemory(Memory memory) {

        if(Objects.nonNull(memory))
            hardwareInfo.setMemory(memory);
        else
            hardwareInfo.setMemory(new MockFactory().getMemories().get(0));

        return this;
    }
}
