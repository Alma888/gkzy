/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.biz;

import com.lj.gkzy.domain.model.UserInfoModel;
import com.lj.gkzy.service.UserInfoService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author liujing
 * @created 2021/5/6
 */
@Service
public class UserBiz {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 进行加密的对象
     */
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 查询userName是否已经被占用
     * @param userName 用户设置的userName
     * @return 是否被占用
     */
    public boolean userNameIsExist(String userName) {
        return userInfoService.queryUserNameIsExist(userName);
    }

    /**
     * 用户注册
     * @param userInfoModel 请求
     */
    public void addUser(UserInfoModel userInfoModel) {
        // 存储非对称加密后的密码
        //userInfoModel.setPassword(passwordEncoder.encode(userInfoModel.getPassword()));
        userInfoService.insert(userInfoModel);
    }

    /**
     * 用户进行登录
     * @param userInfoModel 请求
     * @return 密码是否正确
     */
    public boolean login(UserInfoModel userInfoModel) {
        List<UserInfoModel> infoModels = userInfoService.getUserNameList(userInfoModel.getUserName());
        if ((infoModels.size() == 1 )) {
            return true;
        } else {
            return false;
        }
    }
}
