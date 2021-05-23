
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.controller;

import com.lj.gkzy.biz.VolunteerBiz;
import com.lj.gkzy.common.response.Paging;
import com.lj.gkzy.common.response.PagingResponse;
import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import com.lj.gkzy.domain.model.VolunteerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.lj.gkzy.service.converter.ObtainingScoreDataModelConverter.convertToVolunteerRecommendVOs;

/**
 * 志愿相关对外提供Http接口
 *
 * @author liujing
 * @created 2021/5/5
 */
@Slf4j
@RestController
@RequestMapping("/api/volunteer/")
public class VolunteerController {

    @Resource
    private VolunteerBiz volunteerBiz;

    @ResponseBody
    @GetMapping("/recommend")
    public PagingResponse recommend(@RequestBody VolunteerModel volunteerModel) {
        Integer score=volunteerModel.getScore();
        Integer offset=volunteerModel.getOffset();
        Integer limit=volunteerModel.getLimit();
        // 参数校验
        if (score == null || score < 0) {
            return PagingResponse.buildFailure("请求参数错误");
        }
        try {
            List<ObtainingScoreDataModel> recommendList = volunteerBiz.allRecommend(score, offset, limit);
            int totalRecord = volunteerBiz.getRecommendTotalRecord(score);
            return PagingResponse.buildSuccess(
                    convertToVolunteerRecommendVOs(recommendList),
                    new Paging(offset, limit, totalRecord, totalRecord > limit + offset));
        } catch (Exception e) {
            log.error("VolunteerController.pageData | 推荐志愿时出现异常, score:{}, e=", score, e);
            return PagingResponse.buildFailure(e.getMessage());
        }
    }
}
