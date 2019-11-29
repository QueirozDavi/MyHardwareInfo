package com.ustore.mhinfo.config;

import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.HardwareInfo;
import com.ustore.mhinfo.domain.Memory;
import com.ustore.mhinfo.domain.dto.HardwareSummaryDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.management.ThreadInfo;

@Configuration
public class ModelMapperConfig {

    private static final long CONVERT_NUMBER = 1073741824;

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(getMapMyHardwareSummaryDTOMapper(), HardwareInfo.class, HardwareSummaryDTO.class);
        modelMapper.addConverter(getMapCpuInfoMapper(), ThreadInfo.class, CpuInfo.class);
        return modelMapper;
    }

    private Converter<ThreadInfo, CpuInfo> getMapCpuInfoMapper() {
        return context -> {

            ThreadInfo source = context.getSource();
            CpuInfo cpuInfo = new CpuInfo();
            cpuInfo.setThreadName(source.getThreadName());
            cpuInfo.setThreadState(source.getThreadState().name());

            return cpuInfo;
        };
    }

    private Converter<HardwareInfo, HardwareSummaryDTO> getMapMyHardwareSummaryDTOMapper() {
        return context -> {
            HardwareInfo source = context.getSource();
            HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTO();
            setDiskInformation(hardwareSummaryDTO, source.getDisk());
            setMemoryInformation(hardwareSummaryDTO, source.getMemory());
            hardwareSummaryDTO.setCpuInfos(source.getCpuInfos());

            return hardwareSummaryDTO;
        };
    }

    private void setMemoryInformation(HardwareSummaryDTO hardwareSummaryDTO, Memory memory) {
        hardwareSummaryDTO.setInitialMemory(String.format("Initial memory: %.2f GB",
                memory.getInitialMemory() /CONVERT_NUMBER));
        hardwareSummaryDTO.setUsedHeapMemory(String.format("Used heap memory: %.2f GB",
                memory.getUsedHeapMemory() /CONVERT_NUMBER));
        hardwareSummaryDTO.setMaxHeapMemory(String.format("Max heap memory: %.2f GB",
                memory.getMaxHeapMemory() /CONVERT_NUMBER));
        hardwareSummaryDTO.setCommittedHeapMemory(String.format("Committed memory: %.2f GB",
                memory.getCommittedMemory() /CONVERT_NUMBER));
    }

    private void setDiskInformation(HardwareSummaryDTO hardwareSummaryDTO, Disk disk) {
        hardwareSummaryDTO.setFreeDiskSpace(String.format("Free space: %.2f GB",
                (double) disk.getFreeSpace() / CONVERT_NUMBER));
        hardwareSummaryDTO.setTotalDiskSpace(String.format("Total space: %.2f GB",
                (double) disk.getTotalDiskSpace() / CONVERT_NUMBER));
        hardwareSummaryDTO.setUsableDiskSpace(String.format("Usable space: %.2f GB",
                (double) disk.getUsableSpace() / CONVERT_NUMBER));
    }

}
