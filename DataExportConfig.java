package com.dorm.config.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "dorm.web.export")
public class DataExportConfig {
    Boolean enable = true;
    Integer interval = 5;
}
