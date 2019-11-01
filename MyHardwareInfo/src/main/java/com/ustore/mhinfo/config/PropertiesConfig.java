package com.ustore.mhinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PropertiesConfig {

    @Bean
    public void defineOperationalSystemProperties(){
        Properties prop = new Properties();
        prop.put("system.windows", "C:");
        prop.put("system.linux", "/");
    }
}
