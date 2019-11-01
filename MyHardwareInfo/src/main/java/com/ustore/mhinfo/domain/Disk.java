package com.ustore.mhinfo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Disk {

    private long totalDiskSpace;
    private long freeSpace;
    private long usableSpace;

}
