package com.ustore.mhinfo.config;

import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.HardwareSummary;
import com.ustore.mhinfo.domain.dto.HardwareSummaryDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(getMapMyHardwareSummaryDTOMapper(), HardwareSummary.class, HardwareSummaryDTO.class);
        return modelMapper;
    }

    private Converter<HardwareSummary, HardwareSummaryDTO> getMapMyHardwareSummaryDTOMapper() {
        return context -> {
            HardwareSummary source = context.getSource();
            HardwareSummaryDTO hardwareSummaryDTO = new HardwareSummaryDTO();
            setDiskInformation(hardwareSummaryDTO, source.getDisk());

            return hardwareSummaryDTO;
        };
    }

    private void setDiskInformation(HardwareSummaryDTO hardwareSummaryDTO, Disk disk) {
        hardwareSummaryDTO.setFreeDiskSpace(String.format("Free space: %.2f GB",
                (double) disk.getFreeSpace() / 1073741824));
        hardwareSummaryDTO.setTotalDiskSpace(String.format("Total space: %.2f GB",
                (double) disk.getTotalDiskSpace() / 1073741824));
        hardwareSummaryDTO.setUsableDiskSpace(String.format("Usable space: %.2f GB",
                (double) disk.getUsableSpace() / 1073741824));
    }

}
