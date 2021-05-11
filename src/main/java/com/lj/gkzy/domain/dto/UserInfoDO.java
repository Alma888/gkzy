package com.lj.gkzy.domain.dto;

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
public class UserInfoDO implements Serializable {
    /**
     * ID
     */
    private Long id;
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
    /**
     * 用户状态 1:正常用户,2:注销用户
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date modified;
}
