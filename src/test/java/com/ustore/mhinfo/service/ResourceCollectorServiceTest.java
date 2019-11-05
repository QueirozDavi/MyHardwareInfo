package com.ustore.mhinfo.service;

import com.ustore.mhinfo.MyHardwareInfoApplication;
import com.ustore.mhinfo.config.MockFactory;
import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.Disk;
import com.ustore.mhinfo.domain.HardwareSummary;
import com.ustore.mhinfo.domain.Memory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.CollectionUtils;

import java.lang.management.ThreadInfo;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {MyHardwareInfoApplication.class,})
public class ResourceCollectorServiceTest {

    @InjectMocks
    @Autowired
    private ResourceCollectorService resourceCollectorService;
    @Mock
    private ModelMapper modelMapper;
    private final double delta = 0.01;

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(resourceCollectorService, "operationalSystem", "C:/");
    }

    @Test
    public void shouldReturnHardwareSummaryWhenCallResourceCollectorService_getHardwareSummary() {

        when(modelMapper.map(ThreadInfo.class, CpuInfo.class)).thenReturn(new MockFactory().getCpuInfos().get(0));

        HardwareSummary hardwareSummary = resourceCollectorService.getHardwareSummary();

        assertDisk(hardwareSummary.getDisk());
        assertCpuInfos(hardwareSummary.getCpuInfos());
        assertMemory(hardwareSummary.getMemory());
    }

    private void assertMemory(Memory memory) {
        assertNotNull(memory);
        assertTrue(memory.getUsedHeapMemory() > 0);
        assertTrue(memory.getMaxHeapMemory() > 0);
        assertTrue(memory.getCommittedMemory() > 0);
        assertTrue(memory.getInitialMemory() > 0);
    }

    private void assertDisk(Disk disk) {
        assertNotNull(disk);
        assertTrue(disk.getUsableSpace() > 0);
        assertTrue(disk.getFreeSpace() > 0);
        assertTrue(disk.getTotalDiskSpace() > 0);
    }

    private void assertCpuInfos(List<CpuInfo> cpuInfos) {
        assertFalse(CollectionUtils.isEmpty(cpuInfos));
        assertTrue(cpuInfos.size() > 0);

        for (CpuInfo cpuInfo : cpuInfos) {
            assertNotNull(cpuInfo.getThreadState());
            assertNotNull(cpuInfo.getThreadName());
            assertNotNull(cpuInfo.getCpuTime());
        }
    }
}
