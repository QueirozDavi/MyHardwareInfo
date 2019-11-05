package com.ustore.mhinfo.domain;


import com.ustore.mhinfo.domain.builder.DiskBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiskTest {

    private long totalDiskSpace;
    private long freeSpace;
    private long usableSpace;
    private final long emptyParameter = 0;

    @Before
    public void setUp() {
        this.freeSpace = 12345678;
        this.totalDiskSpace = 123456789;
        this.usableSpace = 1000;
    }

    @Test
    public void shouldCreateDiskWithAllParameters() {
        Disk disk = new DiskBuilder()
                .withTotalDiskSpace(totalDiskSpace)
                .withUsableSpace(usableSpace)
                .withFreeSpace(freeSpace)
                .build();

        assertTrue(Objects.nonNull(disk));
        getAssertsTotalDiskSpace(disk);
        getAssertsFreeSpaceOnly(disk);
        getAssertsUsableSpace(disk);
    }

    @Test( expected = IllegalArgumentException.class)
    public void shouldNotCreateNotDiskWithOuAllParameters() {
        Disk disk = new DiskBuilder().build();
    }

    @Test
    public void shouldCreateDiskWithTotalDiskSpaceOnly() {
        Disk disk = new DiskBuilder()
                .withTotalDiskSpace(totalDiskSpace)
                .build();

        assertTrue(Objects.nonNull(disk));
        getAssertsTotalDiskSpace(disk);
    }

    @Test
    public void shouldCreateDiskWithFreeSpaceOnly() {
        Disk disk = new DiskBuilder()
                .withFreeSpace(freeSpace)
                .build();

        assertTrue(Objects.nonNull(disk));
        getAssertsFreeSpaceOnly(disk);
    }

    @Test
    public void shouldCreateDiskWithUsableSpaceOnly() {
        Disk disk = new DiskBuilder()
                .withUsableSpace(usableSpace)
                .build();

        assertTrue(Objects.nonNull(disk));
        getAssertsUsableSpace(disk);
    }

    private void getAssertsUsableSpace(Disk disk) {
        assertTrue(disk.getUsableSpace() > emptyParameter);
        assertEquals(disk.getUsableSpace(), usableSpace);
    }

    private void getAssertsTotalDiskSpace(Disk disk) {
        assertTrue(disk.getTotalDiskSpace() > emptyParameter);
        assertEquals(disk.getTotalDiskSpace(), totalDiskSpace);
    }

    private void getAssertsFreeSpaceOnly(Disk disk) {
        assertTrue(disk.getFreeSpace() > emptyParameter);
        assertEquals(disk.getFreeSpace(), freeSpace);
    }
}
