package com.ustore.mhinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HardwareSummary {

    private Disk disk;
    private Memory memory;

}
