package com.lj.gkzy.dao;

import com.lj.gkzy.domain.dto.UserInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(UserInfo)表数据库访问层
 *
 * @author liujing
 * @since 2021-05-06 08:59:37
 */
public interface UserInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserInfoDO queryById(@Param("id")Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserInfoDO> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userInfoDO 实例对象
     * @return 对象列表
     */
    List<UserInfoDO> queryAll(UserInfoDO userInfoDO);

    /**
     * 新增数据
     *
     * @param userInfo 实例对象
     * @return 影响行数
     */
    int insert(UserInfoDO userInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserInfoDO> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserInfo> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserInfoDO> entities);

    /**
     * 修改数据
     *
     * @param userInfo 实例对象
     * @return 影响行数
     */
    int update(UserInfoDO userInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询用户名为入参的记录行数量
     * @param userName 入参用户名
     * @return 记录行数量
     */
    List<UserInfoDO> queryUserNameList(@Param("userName") String userName);
}
