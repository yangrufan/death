package com.dorm.config.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "dorm.web.oss")
public class OssConfig {
    private String accessKeyId;
    private String accessKeySecret;
}
