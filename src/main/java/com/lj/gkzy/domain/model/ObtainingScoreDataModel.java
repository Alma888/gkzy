package com.lj.gkzy.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (ObtainingScoreData)实体类
 *
 * @author liujing
 * @since 2021-04-03 13:58:42
 */
@Data
public class ObtainingScoreDataModel implements Serializable {
    /**
     * 主键
     */
    private Integer id;
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
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;
}