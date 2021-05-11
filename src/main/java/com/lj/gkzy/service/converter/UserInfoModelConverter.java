/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.service.converter;

import com.lj.gkzy.domain.dto.UserInfoDO;
import com.lj.gkzy.domain.model.UserInfoModel;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author liujing
 * @created ${DATE}
 */
public class UserInfoModelConverter {

    /**
     * Convert UserInfoDO to UserInfoModel
     * @param userInfoDO  UserInfoDO
     * @return  UserInfoModel
     */
    public static UserInfoModel convertToUserInfoModel(UserInfoDO userInfoDO) {
        if (userInfoDO == null) {
            return null;
        }
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setId(userInfoDO.getId());
        userInfoModel.setUserName(userInfoDO.getUserName());
        userInfoModel.setPassword(userInfoDO.getPassword());
        userInfoModel.setEmail(userInfoDO.getEmail());
        userInfoModel.setPhone(userInfoDO.getPhone());
        userInfoModel.setIdentity(userInfoDO.getIdentity());
        userInfoModel.setStatus(userInfoDO.getStatus());
        userInfoModel.setCreated(userInfoDO.getCreated());
        userInfoModel.setModified(userInfoDO.getModified());

        return userInfoModel;
    }

    /**
     * Convert List<UserInfoDO> to List<UserInfoModel>
     * @param list  UserInfoDO list
     * @return  List<UserInfoModel>
     */
    public static List<UserInfoModel> convertToUserInfoModelList(List<UserInfoDO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        List<UserInfoModel> models = Lists.newArrayListWithCapacity(list.size());
        list.forEach(data -> models.add(convertToUserInfoModel(data)));

        return models;
    }

    /**
     * Convert UserInfoModel to UserInfoDO
     * @param userInfoModel  UserInfoModel
     * @return  UserInfoDO
     */
    public static UserInfoDO convertToUserInfoDO(UserInfoModel userInfoModel) {
        if (userInfoModel == null) {
            return null;
        }
        UserInfoDO userInfoDO = new UserInfoDO();

        userInfoDO.setId(userInfoModel.getId());
        userInfoDO.setUserName(userInfoModel.getUserName());
        userInfoDO.setPassword(userInfoModel.getPassword());
        userInfoDO.setEmail(userInfoModel.getEmail());
        userInfoDO.setPhone(userInfoModel.getPhone());
        userInfoDO.setIdentity(userInfoModel.getIdentity());
        userInfoDO.setStatus(userInfoModel.getStatus());
        userInfoDO.setCreated(userInfoModel.getCreated());
        userInfoDO.setModified(userInfoModel.getModified());

        return userInfoDO;
    }

    /**
     * Convert List<UserInfoModel> to List<UserInfoDO>
     * @param models UserInfoModel list
     * @return  List<UserInfoDO>
     */
    public static List<UserInfoDO> convertToUserInfoDOList(List<UserInfoModel> models) {
        if (CollectionUtils.isEmpty(models)) {
            return Lists.newArrayList();
        }

        List<UserInfoDO> datas = Lists.newArrayListWithCapacity(models.size());
        models.forEach(model -> datas.add(convertToUserInfoDO(model)));

        return datas;
    }
}

