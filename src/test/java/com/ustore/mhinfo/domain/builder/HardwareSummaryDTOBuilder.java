package com.ustore.mhinfo.domain.builder;

import com.ustore.mhinfo.config.MockFactory;
import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.dto.HardwareSummaryDTO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class HardwareSummaryDTOBuilder {

    private HardwareSummaryDTO hardwareSummaryDTO;

    public HardwareSummaryDTOBuilder() {
        hardwareSummaryDTO = new HardwareSummaryDTO();
    }

    public HardwareSummaryDTO build() {
        if (Objects.isNull(hardwareSummaryDTO.getTotalDiskSpace()) &&
                Objects.isNull(hardwareSummaryDTO.getFreeDiskSpace()) &&
                Objects.isNull(hardwareSummaryDTO.getUsableDiskSpace()) &&
                Objects.isNull(hardwareSummaryDTO.getInitialMemory()) &&
                Objects.isNull(hardwareSummaryDTO.getUsedHeapMemory()) &&
                Objects.isNull(hardwareSummaryDTO.getMaxHeapMemory()) &&
                Objects.isNull(hardwareSummaryDTO.getCommittedHeapMemory()) &&
                CollectionUtils.isEmpty(hardwareSummaryDTO.getCpuInfos()))
            throw new IllegalArgumentException("At least one parameter must be set.");

        return hardwareSummaryDTO;
    }

    public HardwareSummaryDTOBuilder withTotalDiskSpace(String totalDiskSpace) {
        hardwareSummaryDTO.setTotalDiskSpace(totalDiskSpace);
        return this;
    }

    public HardwareSummaryDTOBuilder withFreeDiskSpace(String freeDiskSpace) {
        hardwareSummaryDTO.setFreeDiskSpace(freeDiskSpace);
        return this;
    }
    public HardwareSummaryDTOBuilder withUsedHeapMemory(String usedHeapMemory) {
        hardwareSummaryDTO.setUsedHeapMemory(usedHeapMemory);
        return this;
    }

    public HardwareSummaryDTOBuilder withUsableDiskSpace(String usableDiskSpace) {
        hardwareSummaryDTO.setUsableDiskSpace(usableDiskSpace);
        return this;
    }

    public HardwareSummaryDTOBuilder withInitialMemory(String initialHeapMemory) {
        hardwareSummaryDTO.setInitialMemory(initialHeapMemory);
        return this;
    }

    public HardwareSummaryDTOBuilder withMaxHeapMemory(String maxHeapMemory) {
        hardwareSummaryDTO.setMaxHeapMemory(maxHeapMemory);
        return this;
    }

    public HardwareSummaryDTOBuilder withCommittedHeapMemory(String committedHeapMemory) {
        hardwareSummaryDTO.setCommittedHeapMemory(committedHeapMemory);
        return this;
    }

    public HardwareSummaryDTOBuilder withCpuInfos(List<CpuInfo> cpuInfos) {

        if(CollectionUtils.isEmpty(cpuInfos))
            hardwareSummaryDTO.setCpuInfos(new MockFactory().getCpuInfos());
        else
            hardwareSummaryDTO.setCpuInfos(cpuInfos);

        return this;
    }
}
