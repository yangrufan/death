package com.dorm.config.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class DormWebConfig {
    @Value("debug")
    String debug = "false";
    @Value("dorm.web.permission")
    String enablePermissionCheck = "true";
}
