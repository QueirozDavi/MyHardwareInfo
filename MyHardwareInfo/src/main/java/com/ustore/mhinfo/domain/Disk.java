package com.ustore.mhinfo.domain;

import lombok.Data;

@Data
public class Disk {

    private long totalDiskSpace;
    private long freeSpace;
    private long usableSpace;

}
