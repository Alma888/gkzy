package com.lj.gkzy.service;

import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ObtainingScoreDataModel)表服务接口
 *
 * @author liujing
 * @since 2021-04-03 13:58:43
 */
public interface ObtainingScoreDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ObtainingScoreDataModel queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ObtainingScoreDataModel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ObtainingScoreDataModel 实例对象
     * @return 实例对象
     */
    ObtainingScoreDataModel insert(ObtainingScoreDataModel ObtainingScoreDataModel);

    /**
     * 批量插入数据
     * @param obtainingScoreDataModelList 数据集合
     * @return 影响的行数
     */
    int insertBatch(List<ObtainingScoreDataModel> obtainingScoreDataModelList);

    /**
     * 修改数据
     *
     * @param ObtainingScoreDataModel 实例对象
     * @return 实例对象
     */
    ObtainingScoreDataModel update(ObtainingScoreDataModel ObtainingScoreDataModel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 清空表
     */
    void truncateTable();
    /**
     * 推荐志愿service方法
     * @param score 用户分数
     * @param offset 分页参数
     * @param limit 分页参数
     * @return 符合条件的List集合
     */
    List<ObtainingScoreDataModel> allRecommend(Integer score, Integer offset, Integer limit);

    /**
     * 获取满足分数大于用户分数的记录行数量
     * @param score 用户分数
     * @return 符合条件的List集合
     */
    List<ObtainingScoreDataModel> getRecommendTotalRecord(Integer score);

    /**
     * 分页数据
     * @param offset 分页参数
     * @param limit 分页参数
     * @return 数据集
     */
    List<ObtainingScoreDataModel> pageData(Integer offset, Integer limit);

    /**
     * 获取分页全部数据量
     * @return 数据行数量
     */
    Integer totalPageData();

}