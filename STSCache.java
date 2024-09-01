package com.dorm.common;

import com.dorm.aliyun.STS;
import com.dorm.config.aliyun.AliyunMTConfig;
import com.dorm.config.aliyun.AliyunOSSConfig;
import com.dorm.config.aliyun.AliyunSTSConfig;
import com.dorm.web.vo.aliyun.OSSSTS;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Data
public class STSCache {
    private static Map<String, OSSSTS> ossSTSMap = new HashMap<>();
    public static AliyunSTSConfig stsConfig;
    public static AliyunOSSConfig ossConfig;
    public static AliyunMTConfig mtConfig;
    public static OSSSTS getOSSSTS(String name) throws Exception {
        if (ossSTSMap.containsKey(name) && ossSTSMap.get(name).getExpireTime().isAfter(LocalDateTime.now())) {
            return ossSTSMap.get(name);
        }
        var body = new STS(mtConfig, stsConfig, ossConfig).getOSSSTSToken(name).body;

        return OSSSTS.builder()
                .token(body.getCredentials().getSecurityToken())
                .accessKeyId(body.getCredentials().getAccessKeyId())
                .accessKeySecret(body.getCredentials().getAccessKeySecret())
                .expireTime(LocalDateTime.parse(body.getCredentials().getExpiration(), DateTimeFormatter.ISO_DATE_TIME))
                .region(ossConfig.getEndpoint())
                .bucketName(ossConfig.getBucketName())
                .build();
    }
}
