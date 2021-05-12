package com.lj.gkzy.dao;

import com.lj.gkzy.domain.dto.ObtainingScoreDataDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (ObtainingScoreData)表数据库访问层
 *
 * @author liujing
 * @since 2021-04-03 13:58:43
 */

@Repository
public interface ObtainingScoreDataDao  {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ObtainingScoreDataDO queryById(@Param("id") Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ObtainingScoreDataDO> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param obtainingScoreData 实例对象
     * @return 对象列表
     */
    List<ObtainingScoreDataDO> queryAll(ObtainingScoreDataDO obtainingScoreData);

    /**
     * 新增数据
     *
     * @param obtainingScoreData 实例对象
     * @return 影响行数
     */
    int insert(ObtainingScoreDataDO obtainingScoreData);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ObtainingScoreData> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ObtainingScoreDataDO> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ObtainingScoreData> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<ObtainingScoreDataDO> entities);

    /**
     * 修改数据
     *
     * @param obtainingScoreData 实例对象
     * @return 影响行数
     */
    int update(ObtainingScoreDataDO obtainingScoreData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 清空表
     */
    void truncateTable();

    /**
     * 志愿推荐
     * @param score 用户分数
     * @param offset 分页参数
     * @param limit 分页参数
     * @return 符合条件的List集合
     */
    List<ObtainingScoreDataDO> allRecommend(@Param("score") Integer score,@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 获取所有小于用户分数的集合
     * @param score 用户成绩
     * @return 符合条件的集合
     */
    List<ObtainingScoreDataDO> getRecommendTotalRecord(@Param("score") Integer score);

    /**
     * 分页数据
     * @param offset 分页参数
     * @param limit 分页参数
     * @return 符合的数据集合
     */
    List<ObtainingScoreDataDO> pageData(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 获取分页全部数据量
     * @return 数据行数量
     */
    List<ObtainingScoreDataDO> totalPageData();
}

