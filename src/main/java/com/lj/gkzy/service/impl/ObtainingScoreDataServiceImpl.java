package com.lj.gkzy.service.impl;

import com.lj.gkzy.dao.ObtainingScoreDataDao;
import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import com.lj.gkzy.service.ObtainingScoreDataService;
import com.lj.gkzy.service.converter.ObtainingScoreDataModelConverter;
import org.springframework.stereotype.Service;
import static com.lj.gkzy.service.converter.ObtainingScoreDataModelConverter.*;
import static com.lj.gkzy.service.converter.ObtainingScoreDataModelConverter.convertToObtainingScoreDataDO;


import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (ObtainingScoreDataModel)表服务实现类
 *
 * @author liujing
 * @since 2021-04-03 13:58:44
 */
@Service
public class ObtainingScoreDataServiceImpl implements ObtainingScoreDataService {

    @Resource
    private ObtainingScoreDataDao obtainingScoreDataDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ObtainingScoreDataModel queryById(Integer id) {
        return convertToObtainingScoreDataModel(obtainingScoreDataDao.queryById(id));
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ObtainingScoreDataModel> queryAllByLimit(int offset, int limit) {
        return obtainingScoreDataDao.queryAllByLimit(offset, limit).
                stream().
                map(ObtainingScoreDataModelConverter::convertToObtainingScoreDataModel)
                .collect(Collectors.toList());
    }

    /**
     * 新增数据
     *
     * @param ObtainingScoreDataModel 实例对象
     * @return 实例对象
     */
    @Override
    public ObtainingScoreDataModel insert(ObtainingScoreDataModel ObtainingScoreDataModel) {
        obtainingScoreDataDao.insert(convertToObtainingScoreDataDO(ObtainingScoreDataModel));
        return ObtainingScoreDataModel;
    }

    @Override
    public int insertBatch(List<ObtainingScoreDataModel> obtainingScoreDataModelList) {
        return obtainingScoreDataDao.insertBatch(convertToObtainingScoreDataDOs(obtainingScoreDataModelList));
    }

    /**
     * 修改数据
     *
     * @param obtainingScoreDataModel 实例对象
     * @return 实例对象
     */
    @Override
    public ObtainingScoreDataModel update(ObtainingScoreDataModel obtainingScoreDataModel) {
        obtainingScoreDataDao.update(convertToObtainingScoreDataDO(obtainingScoreDataModel));
        return queryById(obtainingScoreDataModel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return obtainingScoreDataDao.deleteById(id) > 0;
    }

    @Override
    public void truncateTable() {
        obtainingScoreDataDao.truncateTable();
    }

    @Override
    public List<ObtainingScoreDataModel> allRecommend(Integer score, Integer offset, Integer limit) {
        return convertToObtainingScoreDataModels(obtainingScoreDataDao.allRecommend(score, offset, limit));
    }

    @Override
    public List<ObtainingScoreDataModel> getRecommendTotalRecord(Integer score) {
        return convertToObtainingScoreDataModels(obtainingScoreDataDao.getRecommendTotalRecord(score));
    }

    @Override
    public List<ObtainingScoreDataModel> pageData(Integer offset, Integer limit) {
        return convertToObtainingScoreDataModels(obtainingScoreDataDao.pageData(offset, limit));
    }

    @Override
    public Integer totalPageData() {
        return obtainingScoreDataDao.totalPageData().size();
    }
}
