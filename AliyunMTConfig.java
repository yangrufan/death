package com.dorm.config.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "aliyun.mt")
@Component
public class AliyunMTConfig {
    private String arn;
    private String source = "zh";
    private String target = "en";
    private String scene = "general";
    private String endpoint;
}
