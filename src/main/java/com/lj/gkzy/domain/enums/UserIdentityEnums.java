
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.domain.enums;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 用户类型枚举类
 *
 * @author liujing
 * @created 2021/5/6
 */
public enum UserIdentityEnums {

    /**
     * 管理员
     */
    ADMINISTRATOR(1, "管理员"),

    /**
     * 普通用户
     */
    COMMON_USER(2, "普通用户")
    ;

    /**
     * 用户类型
     */
    private final Integer identity;

    /**
     * 描述
     */
    private final String desc;

    public static final Map<Integer, UserIdentityEnums> VALUE_MAP = Maps.newHashMap();

    static {
        for (UserIdentityEnums e : UserIdentityEnums.values()) {
            VALUE_MAP.put(e.getIdentity(), e);
        }
    }

    UserIdentityEnums(Integer identity, String desc) {
        this.identity = identity;
        this.desc = desc;
    }

    public Integer getIdentity() {
        return identity;
    }

    public String getDesc() {
        return desc;
    }
}