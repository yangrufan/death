package com.dorm.config;

import com.dorm.web.constant.OrderConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "dorm.file.store")
public class FileStorageConfig {
    private String root;
    // TODO 默认值
    private String tempRoot;
    private final List<String> licenses = new ArrayList<>();
    public String getNowTempPath() {
        return tempRoot + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    }
    // The Day Before Yesterday
    public String getTDBYTempPath() {
        return tempRoot + LocalDateTime.now().minusDays(2).format(DateTimeFormatter.ISO_DATE);
    }
    // /order/situation
    public static String getTempOrderImgPath(Integer type, String separator){
        return separator + "order" + separator + OrderConstant.ORDER_IMG_PATH.get(type) ;
    }
    public static String getTempOrderImgPath(Long userId, Integer type, String separator){
        return separator + "order" + separator + OrderConstant.ORDER_IMG_PATH.get(type) + separator + userId.toString();
    }
    // 不使用order/1/situation的原因是方便格式同temp一致
    // /order/situation/1
    public static String getOrderImgPath(Long orderId, Integer type, String separator){
        return separator + "order" + separator + OrderConstant.ORDER_IMG_PATH.get(type) + separator + orderId;
    }
    // /pact
    public static String getTempPactImgPath(String userId, String separator){
        return separator + "pact" + separator + "/" + userId;
    }
    // 不使用pact/1/situation的原因是方便格式同temp一致
    // /pact/1
    public static String getPactImgPath(Long id, String separator){
        return separator + "pact" + separator + id;
    }

}
