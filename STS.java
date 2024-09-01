package com.dorm.aliyun;

import com.aliyun.sts20150401.Client;
import com.aliyun.sts20150401.models.AssumeRoleRequest;
import com.aliyun.sts20150401.models.AssumeRoleResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dorm.common.STSCache;
import com.dorm.config.aliyun.AliyunMTConfig;
import com.dorm.config.aliyun.AliyunOSSConfig;
import com.dorm.config.aliyun.AliyunSTSConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Component
public class STS {
    final AliyunMTConfig aliyunMTConfig;
    final AliyunSTSConfig aliyunSTSConfig;
    final AliyunOSSConfig aliyunOSSConfig;
    private Client createClient() throws Exception {
        Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(aliyunSTSConfig.getAccessKeyId())
                .setAccessKeySecret(aliyunSTSConfig.getAccessKeySecret());
        config.endpoint = aliyunSTSConfig.getEndPoint();
        return new Client(config);
    }
    public AssumeRoleResponse getOSSSTSToken(String user) throws Exception {
        Client client = createClient();
        AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest()
                .setRoleSessionName(user)
                .setRoleArn(aliyunOSSConfig.getArn());
        RuntimeOptions runtime = new RuntimeOptions();
        return client.assumeRoleWithOptions(assumeRoleRequest, runtime);
    }
    public AssumeRoleResponse getMTSTSToken(String user) throws Exception {
        Client client = createClient();
        AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest()
                .setRoleSessionName(user)
                .setRoleArn(aliyunMTConfig.getArn());
        RuntimeOptions runtime = new RuntimeOptions();
        return client.assumeRoleWithOptions(assumeRoleRequest, runtime);
    }
    @PostConstruct
    public void initSTSCache() {
        STSCache.stsConfig = aliyunSTSConfig;
        STSCache.ossConfig = aliyunOSSConfig;
    }
}
