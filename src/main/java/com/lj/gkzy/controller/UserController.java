
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.controller;

import com.lj.gkzy.biz.UserBiz;
import com.lj.gkzy.common.response.ApiResponse;
import com.lj.gkzy.domain.enums.UserIdentityEnums;
import com.lj.gkzy.domain.model.UserInfoModel;
import com.lj.gkzy.domain.vo.UserInfoVO;
import com.lj.gkzy.service.converter.UserInfoVoConverter;
import com.lj.gkzy.service.impl.ObtainingScoreDataServiceImpl;
import com.lj.gkzy.service.impl.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 对外提供服务http接口用户层
 *
 * @author liujing
 * @created 2021/5/6
 */
@Slf4j
@Controller
@RequestMapping("/api/user/")
public class UserController {

    @Resource
    private UserBiz userBiz;

    @Resource
    private UserInfoServiceImpl userSearch;


    @PostMapping("/add")
    public String addUser(UserInfoVO request) {
        try {
            // 参数校验
            checkoutRequest(request);
            checkArgument(!userBiz.userNameIsExist(request.getUserName()), "用户名已存在");
            userBiz.addUser(UserInfoVoConverter.convertToUserInfoModel(request));
            return "redirect:/login2.html";
        } catch (Exception e) {
            log.error("UserController.addUser | 新增用户时出现异常, request:{}, e=", request, e);
            return "注册异常";
        }
    }

    @PostMapping("/login")
    public String login(UserInfoVO request) {
        try {
            // 参数校验
            checkoutRequest(request);
            String pwd;
            Integer shenfen;
            List<UserInfoModel> list = userSearch.getUserNameList(request.getUserName());
            pwd=list.get(0).getPassword();
            shenfen=list.get(0).getIdentity();

            // 判断用户名是否存在
            if (userBiz.userNameIsExist(request.getUserName())) {
                 if (userBiz.login(UserInfoVoConverter.convertToUserInfoModel(request))&&(request.getPassword().equals(pwd))&&request.getIdentity().equals(shenfen)){
                     if (request.getIdentity().equals(1)) {
                         return "redirect:/admin.html";
                     }
                     return "redirect:/index.html";
                }else if (!(request.getPassword().equals(pwd))){
                     return "redirect:/alert_pwderror.html";
                 }else if(!(request.getIdentity().equals(shenfen))){
                     return "redirect:/alert_error.html";
                 }
            }else {
                return "redirect:/noUser.html";
            }
        }catch (Exception e) {
            log.error("UserController.login | 用户登录时出现异常, request:{}, e=", request, e);
//            return ApiResponse.buildFailure(e.getMessage());
            return "redirect:/noUser.html";
        }
        return "redirect:/noUser.html";
    }

//    @PostMapping("login.json")
//    public ApiResponse login(UserInfoVO request) {
//        try {
//            // 参数校验
//            checkoutRequest(request);
//            // 判断用户名是否存在
//            if (userBiz.userNameIsExist(request.getUserName())) {
//                if (userBiz.login(UserInfoVoConverter.convertToUserInfoModel(request))) {
//                    return ApiResponse.buildSuccess();
//                } else {
//                    return ApiResponse.buildFailure("密码错误");
//                }
//            } else {
//                return ApiResponse.buildFailure("用户名不存在");
//            }
//        } catch (Exception e) {
//            log.error("UserController.login | 用户登录时出现异常, request:{}, e=", request, e);
//            return ApiResponse.buildFailure(e.getMessage());
//        }
//    }

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
