package com.ustore.mhinfo.controller.v1;

import com.ustore.mhinfo.domain.HardwareSummary;
import com.ustore.mhinfo.domain.HardwareSummaryDTO;
import com.ustore.mhinfo.service.ResourceCollectorService;
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

    @Autowired
    public HardwareSummaryController(ResourceCollectorService resourceCollectorService) {
        this.resourceCollectorService = resourceCollectorService;
    }

    @GetMapping()
    public HardwareSummaryDTO getHarwareSummary() {
        resourceCollectorService.getHardwareSummary();
        return null;
    }
}
