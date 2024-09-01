package com.dorm.config.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "aliyun.sts")
@Component
public class AliyunSTSConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String endPoint;
    private String regionId;
}
