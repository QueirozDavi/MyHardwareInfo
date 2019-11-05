package com.ustore.mhinfo.domain.builder;

import com.ustore.mhinfo.config.MockFactory;
import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.HardwareSummary;
import com.ustore.mhinfo.domain.Memory;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Data
public class HardwareSummaryBuilder {

    private HardwareSummary hardwareSummary;

    public HardwareSummaryBuilder() {
        hardwareSummary = new HardwareSummary();
    }

    public HardwareSummary build() {
        if (Objects.isNull(hardwareSummary.getDisk()) &&
                Objects.isNull(hardwareSummary.getMemory()) &&
                CollectionUtils.isEmpty(hardwareSummary.getCpuInfos()))
            throw new NullPointerException("At least one parameter must be set.");

        return hardwareSummary;
    }

    public HardwareSummaryBuilder withCupInfos(List<CpuInfo> cupInfos) {

        if (CollectionUtils.isEmpty(cupInfos))
            hardwareSummary.setCpuInfos(cupInfos);
        else
            hardwareSummary.setCpuInfos(new MockFactory().getCpuInfos());

        return this;
    }

    public HardwareSummaryBuilder withDisk(Disk disk) {

        if(Objects.nonNull(disk))
            hardwareSummary.setDisk(disk);
        else
            hardwareSummary.setDisk(new MockFactory().getDisks().get(0));

        return this;
    }

    public HardwareSummaryBuilder withMemory(Memory memory) {

        if(Objects.nonNull(memory))
            hardwareSummary.setMemory(memory);
        else
            hardwareSummary.setMemory(new MockFactory().getMemories().get(0));

        return this;
    }
}
