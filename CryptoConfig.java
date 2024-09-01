package com.dorm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "crypto")
public class CryptoConfig {
    private Boolean enableSessionCrypto;
    private String session_path;
    private Integer session_key_size;
    private String session_algorithm;
    private String jwt_pri_key;
    private String jwt_pub_key;
    private String aes_key;
    public static String aesKey;
}
