package com.lj.gkzy.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表(UserInfo)实体类
 *
 * @author liujing
 * @since 2021-05-06 08:59:37
 */
@Data
public class UserInfoVO implements Serializable {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户密码,bcrypt加密
     */
    private String password;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户类型 1:管理员,2:普通用户
     */
    private Integer identity;
}

