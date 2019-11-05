package com.ustore.mhinfo.config;

import com.ustore.mhinfo.MyHardwareInfoApplication;
import com.ustore.mhinfo.domain.CpuInfo;
import com.ustore.mhinfo.domain.HardwareSummary;
import com.ustore.mhinfo.domain.builder.HardwareSummaryBuilder;
import com.ustore.mhinfo.domain.builder.HardwareSummaryDTOBuilder;
import com.ustore.mhinfo.domain.dto.HardwareSummaryDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {MyHardwareInfoApplication.class,})
public class ModelMapperConfigTest {

    @Autowired
    private ModelMapper modelMapper;
    private ThreadMXBean threadMXBean;
    private HardwareSummary hardwareSummary;

    @Before
    public void setUp() throws Exception {
        this.hardwareSummary = new HardwareSummaryBuilder()
                .withCupInfos(new MockFactory().getCpuInfos())
                .withDisk(new MockFactory().getDisks().get(0))
                .withMemory(new MockFactory().getMemories().get(0))
                .build();

        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.threadMXBean = ManagementFactory.getThreadMXBean();
    }

    @Test
    public void shoulReturnCompleteCpuWhenPassThreadInfo() {

        ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds()[0]);
        CpuInfo cpuInfo = modelMapper.map(threadInfo, CpuInfo.class);

        assertEquals(cpuInfo.getThreadName(), threadInfo.getThreadName());
        assertEquals(cpuInfo.getThreadState(), threadInfo.getThreadState().name());

    }

    @Test
    public void shouldReturnCompleteHardwareSummaryDTOWhenPassHardwareSummary() {

        HardwareSummaryDTO hardwareSummaryDTO = modelMapper.map(this.hardwareSummary, HardwareSummaryDTO.class);
    }

    private void assertCpuInfos(List<CpuInfo> cpuInfos) {
        assertFalse(CollectionUtils.isEmpty(cpuInfos));
        assertTrue(cpuInfos.size() > 0);
        assertEquals(cpuInfos.size(), 4);

        List<CpuInfo> tempCpuInfos = new MockFactory().getCpuInfos();

        for (CpuInfo cpuInfo : cpuInfos) {
            assertEquals(cpuInfo.getThreadState(), tempCpuInfos.get(tempCpuInfos.indexOf(cpuInfo)).getThreadState());
            assertEquals(cpuInfo.getThreadName(), tempCpuInfos.get(tempCpuInfos.indexOf(cpuInfo)).getThreadName());
            assertEquals(cpuInfo.getCpuTime(), tempCpuInfos.get(tempCpuInfos.indexOf(cpuInfo)).getCpuTime());
        }
    }
}
