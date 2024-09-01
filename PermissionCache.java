package com.dorm.common;

import java.util.concurrent.ConcurrentHashMap;

public class PermissionCache {
    public static final ConcurrentHashMap<String, Integer> RoleFileToken = new ConcurrentHashMap<>();
}
