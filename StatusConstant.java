package com.dorm.common;

import java.util.Arrays;
import java.util.List;

public class StatusConstant {
    public static final List<String> BERTH_STATUS_NAME = List.of("空状态,看到这个请报告BUG","可用","已预定","已分配","已出租","维护中");
    public static final Integer BERTH_AVAILABLE = 1;
    public static final Integer BERTH_RESERVED = 2;
    public static final Integer BERTH_ALLOCATED = 3;
    public static final Integer BERTH_RENTED = 4;
    public static final Integer BERTH_MAINTENANCE = 5;
    public static final Integer BERTH_RETIRED = 6;
    public static final Integer BERTH_NEED_CHECK = 7;
    /**
     * 拒绝入住请求
     */
    public static final Integer BERTH_REFUSE_OCCUPY = 8;
    public static final List<String> REQUEST_STATUS_NAME = List.of("空状态,看到这个请报告BUG","");

    public static final Integer EXPORT_QUEUE = 1;
    public static final Integer EXPORT_PROCESS = 2;
    public static final Integer EXPORT_ERROR = 3;
    public static final Integer EXPORT_COMPLETE = 4;
    public static final Integer EXPORT_EMPTY = 5;

}
