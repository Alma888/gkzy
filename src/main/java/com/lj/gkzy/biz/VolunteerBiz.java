
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.biz;

import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import com.lj.gkzy.service.ObtainingScoreDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 志愿服务biz层
 *
 * @author liujing
 * @created 2021/5/5
 */
@Slf4j
@Service
public class VolunteerBiz {

    @Resource
    private ObtainingScoreDataService obtainingScoreDataService;

    /**
     * 根据用户分数进行推荐
     * @param score 用户成绩
     * @param limit 分页参数
     * @param offset 分页参数
     * @return 符合用户条件的List集合
     */
    public List<ObtainingScoreDataModel> allRecommend(Integer score, Integer offset, Integer limit) {
        try {
            // 获取符合条件的数据集合
            return obtainingScoreDataService.allRecommend(score, offset, limit);
        } catch (Exception e) {
            log.error("VolunteerBiz.pageData | 推荐志愿Biz获取数据时出现异常, score:{}, e=", score, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public Integer getRecommendTotalRecord(Integer score) {
        try {
            // 获取符合条件的所有数据集合
            List<ObtainingScoreDataModel> recommendList = obtainingScoreDataService.getRecommendTotalRecord(score);
            Map<String, ObtainingScoreDataModel> lowestRecommendList = recommendList.stream().collect(Collectors.toMap(
                    ObtainingScoreDataModel::difference,
                    Function.identity(),
                    (o1, o2) -> {
                        return o1.getLowestScore() < o2.getLowestScore() ? o1 : o2;
                    }
            ));
            return lowestRecommendList.values().size();
        } catch (Exception e) {
            log.error("VolunteerBiz.pageData | 推荐志愿Biz获取数据时出现异常, score:{}, e=", score, e);
            throw new RuntimeException(e.getMessage());
        }
    }
}