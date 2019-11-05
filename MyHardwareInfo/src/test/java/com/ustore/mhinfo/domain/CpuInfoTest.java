package com.ustore.mhinfo.domain;

import com.ustore.mhinfo.domain.builder.CpuInfoBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class CpuInfoTest {

    private String threadName;
    private String cpuTime;
    private String threadState;


    @Before
    public void setUp() {
        this.cpuTime = "15625000";
        this.threadName = "Reference Handler (Teste)";
        this.threadState = "WAITING";
    }

    @Test
    public void shouldCreateCpuInfoWithAllParameters() {
        CpuInfo cpuInfo = new
                CpuInfoBuilder()
                .withThreadName(threadName)
                .withCpuTime(cpuTime)
                .withThreadState(threadState)
                .build();

        assertTrue(Objects.nonNull(cpuInfo));
        getAssertsCpuTime(cpuInfo);
        getAssertsCpuThreadName(cpuInfo);
        getAssertsCpuThreadState(cpuInfo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateCpuInfoWithOutAllParameters() {
        CpuInfo cpuInfo = new CpuInfoBuilder().build();
    }

    @Test
    public void shouldCreateCpuInfoWithThreadNameOnly() {
        CpuInfo cpuInfo = new
                CpuInfoBuilder()
                .withThreadName(threadName)
                .build();

        assertTrue(Objects.nonNull(cpuInfo));
        getAssertsCpuThreadName(cpuInfo);
    }

    @Test
    public void shouldCreateCpuInfoWithCpuTimeOnly() {
        CpuInfo cpuInfo = new
                CpuInfoBuilder()
                .withCpuTime(cpuTime)
                .build();

        assertTrue(Objects.nonNull(cpuInfo));
        getAssertsCpuTime(cpuInfo);
    }

    @Test
    public void shouldCreateCpuInfoWithCpuThreadStateOnly() {
        CpuInfo cpuInfo = new
                CpuInfoBuilder()
                .withThreadState(threadState)
                .build();

        assertTrue(Objects.nonNull(cpuInfo));
        getAssertsCpuThreadState(cpuInfo);
    }

    private void getAssertsCpuThreadName(CpuInfo cpuInfo) {
        assertNotNull(cpuInfo.getThreadName());
        assertEquals(cpuInfo.getThreadName(), threadName);
    }

    private void getAssertsCpuTime(CpuInfo cpuInfo) {
        assertNotNull(cpuInfo.getCpuTime());
        assertEquals(cpuInfo.getCpuTime(), cpuTime);
    }

    private void getAssertsCpuThreadState(CpuInfo cpuInfo) {
        assertNotNull(cpuInfo.getThreadState());
        assertEquals(cpuInfo.getThreadState(), threadState);
    }
}
