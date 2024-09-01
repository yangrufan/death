package com.dorm.aliyun;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.dorm.config.aliyun.AliyunMTConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MT {
    final AliyunMTConfig aliyunMTConfig;
    final STS sts;
    private static com.aliyun.alimt20181012.Client mtClient;
    private com.aliyun.alimt20181012.Client getMTClient() throws Exception {
        if (mtClient == null) {
            Config credentialConfig = new Config();
            var stsCredential = sts.getMTSTSToken("sdms").body.getCredentials();
            credentialConfig.setEndpoint(aliyunMTConfig.getEndpoint());
            credentialConfig.accessKeyId=stsCredential.getAccessKeyId();
            credentialConfig.accessKeySecret=stsCredential.getAccessKeySecret();
            credentialConfig.setSecurityToken(stsCredential.getSecurityToken());
            mtClient = new com.aliyun.alimt20181012.Client(credentialConfig);
        }
        return mtClient;
    }
    public String getMT(String source) throws Exception {
        com.aliyun.alimt20181012.models.TranslateGeneralRequest translateGeneralRequest = new com.aliyun.alimt20181012.models.TranslateGeneralRequest()
                .setSourceLanguage(aliyunMTConfig.getSource())
                .setTargetLanguage(aliyunMTConfig.getTarget())
                .setScene(aliyunMTConfig.getScene())
                .setSourceText(source)
                .setFormatType("text");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        return getMTClient().translateGeneralWithOptions(translateGeneralRequest, runtime).getBody().getData().getTranslated();
    }
    public String translateWithSnakeCase(String source) throws Exception {
        String translated = getMT(source);
        log.info("translated:{}, split:{}", translated,translated.split(" \""));
        //"^\"[A-Za-z0-9 ]+\""
        return translated.split(" \"")[0].replaceAll("\\.","").replaceAll(" ", "_").toLowerCase();
    }
}
