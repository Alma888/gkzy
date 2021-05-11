/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.service.converter;

import com.lj.gkzy.domain.dto.UserInfoDO;
import com.lj.gkzy.domain.model.UserInfoModel;
import com.lj.gkzy.domain.vo.UserInfoVO;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author liujing
 * @created ${DATE}
 */
public class UserInfoVoConverter {
    /**
     * Convert UserInfoModel to UserInfoVO
     * @param userInfoModel  UserInfoModel
     * @return  UserInfoVO
     */
    public static UserInfoVO convertToUserInfoVO(UserInfoModel userInfoModel) {
        if (userInfoModel == null) {
            return null;
        }
        UserInfoVO userInfoVO = new UserInfoVO();

        userInfoVO.setUserName(userInfoModel.getUserName());
        userInfoVO.setPassword(userInfoModel.getPassword());
        userInfoVO.setEmail(userInfoModel.getEmail());
        userInfoVO.setPhone(userInfoModel.getPhone());
        userInfoVO.setIdentity(userInfoModel.getIdentity());

        return userInfoVO;
    }

    /**
     * Convert List<UserInfoModel> to List<UserInfoVO>
     * @param list  UserInfoModel list
     * @return  List<UserInfoVO>
     */
    public static List<UserInfoVO> convertToUserInfoVOList(List<UserInfoModel> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        List<UserInfoVO> models = Lists.newArrayListWithCapacity(list.size());
        list.forEach(data -> models.add(convertToUserInfoVO(data)));

        return models;
    }

    /**
     * Convert UserInfoVO to UserInfoModel
     * @param userInfoVO  UserInfoVO
     * @return  UserInfoModel
     */
    public static UserInfoModel convertToUserInfoModel(UserInfoVO userInfoVO) {
        if (userInfoVO == null) {
            return null;
        }
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setUserName(userInfoVO.getUserName());
        userInfoModel.setPassword(userInfoVO.getPassword());
        userInfoModel.setEmail(userInfoVO.getEmail());
        userInfoModel.setPhone(userInfoVO.getPhone());
        userInfoModel.setIdentity(userInfoVO.getIdentity());

        return userInfoModel;
    }

    /**
     * Convert List<UserInfoVO> to List<UserInfoModel>
     * @param models UserInfoVO list
     * @return  List<UserInfoModel>
     */
    public static List<UserInfoModel> convertToUserInfoModelList(List<UserInfoVO> models) {
        if (CollectionUtils.isEmpty(models)) {
            return Lists.newArrayList();
        }

        List<UserInfoModel> datas = Lists.newArrayListWithCapacity(models.size());
        models.forEach(model -> datas.add(convertToUserInfoModel(model)));

        return datas;
    }
}