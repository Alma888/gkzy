
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.domain.vo;
import lombok.Data;

/**
 * 在这里编写类的功能描述
 *
 * @author liujing
 * @created 2021/5/5
 */
@Data
public class VolunteerRecommendVO {
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 年份
     */
    private String year;
    /**
     * 省份
     */
    private String provinces;
    /**
     * 录取类别
     */
    private String admissionCategory;
    /**
     * 科类
     */
    private String divisionOfClass;
    /**
     * 批次
     */
    private String batch;
    /**
     * 专业名称
     */
    private String theNameOfTheProfessional;
    /**
     * 专业描述
     */
    private String professionalDescription;
    /**
     * 录取人数
     */
    private Integer enrollment;
    /**
     * 最高分
     */
    private Double highestScore;
    /**
     * 最低分
     */
    private Double lowestScore;
    /**
     * 平均分
     */
    private Double averageScore;
    /**
     * 控制分数线
     */
    private Double controlScore;
    /**
     * 最低分差
     */
    private Double theMinimumGap;
    /**
     * 平均分差
     */
    private Double differenceOfAverage;
}

