package com.lj.gkzy.service.impl;

import com.lj.gkzy.dao.UserInfoDao;
import com.lj.gkzy.domain.model.UserInfoModel;
import com.lj.gkzy.service.UserInfoService;
import com.lj.gkzy.service.converter.UserInfoModelConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(UserInfo)表服务实现类
 *
 * @author liujing
 * @since 2021-05-06 08:59:37
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserInfoModel queryById(Long id) {
        return UserInfoModelConverter.convertToUserInfoModel(userInfoDao.queryById(id));
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserInfoModel> queryAllByLimit(int offset, int limit) {
        return UserInfoModelConverter.convertToUserInfoModelList(userInfoDao.queryAllByLimit(offset, limit));
    }

    /**
     * 新增数据
     *
     * @param userInfoModel 实例对象
     * @return 实例对象
     */
    @Override
    public UserInfoModel insert(UserInfoModel userInfoModel) {
        userInfoDao.insert(UserInfoModelConverter.convertToUserInfoDO(userInfoModel));
        return userInfoModel;
    }

    /**
     * 修改数据
     *
     * @param userInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserInfoModel update(UserInfoModel userInfo) {
        this.userInfoDao.update(UserInfoModelConverter.convertToUserInfoDO(userInfo));
        return this.queryById(userInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userInfoDao.deleteById(id) > 0;
    }

    @Override
    public boolean queryUserNameIsExist(String userName) {
        return userInfoDao.queryUserNameList(userName).size() > 0;
    }

    @Override
    public List<UserInfoModel> getUserNameList(String userName) {
        return UserInfoModelConverter.convertToUserInfoModelList(userInfoDao.queryUserNameList(userName));
    }
}