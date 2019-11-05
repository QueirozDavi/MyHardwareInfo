package com.ustore.mhinfo.domain.builder;

import com.ustore.mhinfo.domain.Disk;

import java.util.Objects;

public class DiskBuilder {

    private Disk disk;

    public DiskBuilder() {
        disk = new Disk();
    }

    public Disk build() {
        if (disk.getTotalDiskSpace() <= 0 &&
                disk.getFreeSpace() <= 0 &&
                disk.getUsableSpace() <= 0)
            throw new IllegalArgumentException("At least one parameter must be set.");

        return disk;
    }

    public DiskBuilder withUsableSpace(long usableSpace) {
        disk.setUsableSpace(usableSpace);
        return this;
    }

    public DiskBuilder withFreeSpace(long freeSpace) {
        disk.setFreeSpace(freeSpace);
        return this;
    }

    public DiskBuilder withTotalDiskSpace(long totalDiskSpace) {
        disk.setTotalDiskSpace(totalDiskSpace);
        return this;
    }

}
