package com.dorm.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import com.dorm.config.aliyun.AliyunOSSConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class OSS {
    final AliyunOSSConfig aliyunOSSConfig;
    final STS sts;
    private static com.aliyun.oss.OSS ossClient;
    private com.aliyun.oss.OSS getOSSClient() throws ClientException {
        if (ossClient == null) {
            var credentialsProvider = CredentialsProviderFactory
                    .newSTSAssumeRoleSessionCredentialsProvider(
                            sts.aliyunSTSConfig.getRegionId(),
                            sts.aliyunSTSConfig.getAccessKeyId(),
                            sts.aliyunSTSConfig.getAccessKeySecret(),
                            sts.aliyunOSSConfig.getArn()
                    );
            ossClient = new OSSClientBuilder().build(
                    aliyunOSSConfig.getEndpoint(),
                    credentialsProvider
            );
        }
        return ossClient;
    }
    public URL getAccessURL(String fileName) throws ClientException, OSSException {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(15L);
        URL url = getOSSClient().generatePresignedUrl(aliyunOSSConfig.getBucketName(), fileName, Date.from(expiration.atZone(zoneId).toInstant()));
        return url;
    }
}
