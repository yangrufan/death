package com.dorm.config.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "aliyun.oss")
@Component
public class AliyunOSSConfig {
    private String arn;
    private String bucketName;
    private String endpoint;
}
