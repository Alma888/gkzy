package com.lj.gkzy.service;

import com.lj.gkzy.domain.model.UserInfoModel;

import java.util.List;

/**
 * 用户信息表(UserInfo)表服务接口
 *
 * @author liujing
 * @since 2021-05-06 08:59:37
 */
public interface UserInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserInfoModel queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserInfoModel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userInfoModel 实例对象
     * @return 实例对象
     */
    UserInfoModel insert(UserInfoModel userInfoModel);

    /**
     * 修改数据
     *
     * @param userInfoModel 实例对象
     * @return 实例对象
     */
    UserInfoModel update(UserInfoModel userInfoModel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据userName查询是否该用户名是否已经存在
     * @param userName 用户名称
     * @return 是否已存在
     */
    boolean queryUserNameIsExist(String userName);

    /**
     * 获取用户名为userName的list集合
     * @param userName 用户名称
     * @return 用户名为userName的list集合
     */
    List<UserInfoModel> getUserNameList(String userName);
}
