
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.gkzy.biz.ObtainingScoreBiz;
import com.lj.gkzy.common.response.Paging;
import com.lj.gkzy.common.response.PagingResponse;
import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import com.lj.gkzy.service.ObtainingScoreDataService;
import com.lj.gkzy.service.converter.ObtainingScoreDataModelConverter;
import com.lj.gkzy.service.impl.ObtainingScoreDataServiceImpl;
import com.sun.scenario.effect.Crop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lj.gkzy.service.converter.ObtainingScoreDataModelConverter.convertToVolunteerRecommendVOs;

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
    private ObtainingScoreDataServiceImpl obt;
    @GetMapping("/alldata")
    public String query() throws JsonProcessingException {
        List<ObtainingScoreDataModel> list =obt.queryAllByLimit(0,20);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = "{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": 1000,\n" +
                "  \"data\": [";
        for (ObtainingScoreDataModel obt : list) {
            s += objectMapper.writeValueAsString(obt);
            s += ",";
        }
        s = s.substring(0, s.length() -1);
        s += " ]\n" +
                "}";
        return s;
    }

    @Resource
    private ObtainingScoreBiz obtainingScoreBiz;

    @GetMapping("/pageData")
    public PagingResponse pageData() {
        // 参数校验o
        try {
            List<ObtainingScoreDataModel> dataModelList = obtainingScoreBiz.pageData(0, 100);
            int total = obtainingScoreBiz.getTotalData();
            return PagingResponse.buildSuccess(convertToVolunteerRecommendVOs(dataModelList), new Paging(0, 100, total, total > 0 + 100));
        } catch (Exception e) {
            log.error("ObtainingScoreController.pageData | 获取分数线分页数据失败, offset:{}, limit:{}, e=",0, 100, e);
            return PagingResponse.buildFailure(e.getMessage());
        }
    }
//    @GetMapping("pageData.json")
//    public PagingResponse pageData(@RequestParam("offset") Integer offset,
//                                   @RequestParam("limit") Integer limit) {
//        // 参数校验
//        if (offset < 0 || limit < 0) {
//            return PagingResponse.buildFailure("请求参数错误");
//        }
//        try {
//            List<ObtainingScoreDataModel> dataModelList = obtainingScoreBiz.pageData(offset, limit);
//            int total = obtainingScoreBiz.getTotalData();
//            return PagingResponse.buildSuccess(convertToVolunteerRecommendVOs(dataModelList), new Paging(offset, limit, total, total > offset + limit));
//        } catch (Exception e) {
//            log.error("ObtainingScoreController.pageData | 获取分数线分页数据失败, offset:{}, limit:{}, e=",offset, limit, e);
//            return PagingResponse.buildFailure(e.getMessage());
//        }
//    }
}