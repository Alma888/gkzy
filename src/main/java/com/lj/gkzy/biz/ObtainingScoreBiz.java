/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.biz;

import com.lj.gkzy.domain.model.ObtainingScoreDataModel;
import com.lj.gkzy.service.ObtainingScoreDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 录取分数相关biz层
 *
 * @author liujing
 * @created 2021/5/5
 */
@Service
public class ObtainingScoreBiz {

    @Resource
    private ObtainingScoreDataService obtainingScoreDataService;

    public List<ObtainingScoreDataModel> pageData(Integer offset, Integer limit) {
        return obtainingScoreDataService.pageData(offset, limit);
    }

    public Integer getTotalData() {
        return obtainingScoreDataService.totalPageData();
    }
}

