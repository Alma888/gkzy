
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.provider.controller;

import com.lj.gkzy.biz.ObtainingScoreBiz;
import com.lj.gkzy.common.response.Paging;
import com.lj.gkzy.common.response.PagingResponse;
import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.lj.gkzy.service.converter.ObtainingScoreDataModelConverter.*;

/**
 * 在这里编写类的功能描述
 *
 * @author liujing
 * @created 2021/5/5
 */
@Slf4j
@RestController
@RequestMapping("/api/score/")
public class ObtainingScoreController {

    @Resource
    private ObtainingScoreBiz obtainingScoreBiz;

    @GetMapping("pageData.json")
    public PagingResponse pageData(@RequestParam("offset") Integer offset,
                                   @RequestParam("limit") Integer limit) {
        // 参数校验
        if (offset < 0 || limit < 0) {
            return PagingResponse.buildFailure("请求参数错误");
        }
        try {
            List<ObtainingScoreDataModel> dataModelList = obtainingScoreBiz.pageData(offset, limit);
            int total = obtainingScoreBiz.getTotalData();
            return PagingResponse.buildSuccess(convertToVolunteerRecommendVOs(dataModelList), new Paging(offset, limit, total, total > offset + limit));
        } catch (Exception e) {
            log.error("ObtainingScoreController.pageData | 获取分数线分页数据失败, offset:{}, limit:{}, e=",offset, limit, e);
            return PagingResponse.buildFailure(e.getMessage());
        }
    }
}