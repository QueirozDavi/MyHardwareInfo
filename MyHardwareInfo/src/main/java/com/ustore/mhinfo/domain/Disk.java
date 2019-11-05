package com.ustore.mhinfo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
public class Disk {

    private long totalDiskSpace;
    private long freeSpace;
    private long usableSpace;

    public Disk (File file) {
        this.setTotalDiskSpace(file.getTotalSpace());
        this.setFreeSpace(file.getFreeSpace());
        this.setUsableSpace(file.getUsableSpace());
    }

}
