
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.provider.controller;

import com.lj.gkzy.biz.UserBiz;
import com.lj.gkzy.common.response.ApiResponse;
import com.lj.gkzy.domain.enums.UserIdentityEnums;
import com.lj.gkzy.domain.vo.UserInfoVO;
import com.lj.gkzy.service.converter.UserInfoVoConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 对外提供服务http接口用户层
 *
 * @author liujing
 * @created 2021/5/6
 */
@Slf4j
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Resource
    private UserBiz userBiz;

    @PostMapping("add.json")
    public ApiResponse addUser(@RequestBody UserInfoVO request) {
        try {
            // 参数校验
            checkoutRequest(request);
            checkArgument(!userBiz.userNameIsExist(request.getUserName()), "用户名已存在");
            userBiz.addUser(UserInfoVoConverter.convertToUserInfoModel(request));
            return ApiResponse.buildSuccess();
        } catch (Exception e) {
            log.error("UserController.addUser | 新增用户时出现异常, request:{}, e=", request, e);
            return ApiResponse.buildFailure(e.getMessage());
        }
    }

    @PostMapping("login.json")
    public ApiResponse login(@RequestBody UserInfoVO request) {
        try {
            // 参数校验
            checkoutRequest(request);
            // 判断用户名是否存在
            if (userBiz.userNameIsExist(request.getUserName())) {
                if (userBiz.login(UserInfoVoConverter.convertToUserInfoModel(request))) {
                    return ApiResponse.buildSuccess();
                } else {
                    return ApiResponse.buildFailure("密码错误");
                }
            } else {
                return ApiResponse.buildFailure("用户名不存在");
            }
        } catch (Exception e) {
            log.error("UserController.login | 用户登录时出现异常, request:{}, e=", request, e);
            return ApiResponse.buildFailure(e.getMessage());
        }
    }

    /**
     * 对入参进行校验
     * @param request 入参请求
     */
    private void checkoutRequest(UserInfoVO request) {
        checkArgument(request != null, "请求不能为空");
        checkArgument(!Strings.isBlank(request.getUserName()), "用户名不能为空");
        checkArgument(!Strings.isBlank(request.getPassword()), "用户密码不能为空");
        checkArgument(UserIdentityEnums.VALUE_MAP.keySet().contains(request.getIdentity()), "身份类型错误");
    }
}
