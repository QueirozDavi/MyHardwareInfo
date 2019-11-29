package com.ustore.mhinfo.controller.v1;

import com.ustore.mhinfo.domain.HardwareInfo;
import com.ustore.mhinfo.domain.dto.HardwareSummaryDTO;
import com.ustore.mhinfo.service.ResourceCollectorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hardware-summary")
@CrossOrigin(origins = "*")
public class HardwareSummaryController {

    private final ResourceCollectorService resourceCollectorService;
    private final ModelMapper modelMapper;

    @Autowired
    public HardwareSummaryController(ResourceCollectorService resourceCollectorService, ModelMapper modelMapper) {
        this.resourceCollectorService = resourceCollectorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public HardwareInfo getHardwareInfo() {
        return resourceCollectorService.getHardwareSummary();
    }

    @GetMapping("/summary")
    public HardwareSummaryDTO getHardwareSummary() {
        return modelMapper.map(resourceCollectorService.getHardwareSummary(), HardwareSummaryDTO.class);
    }
}
