package com.lj.gkzy.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * (VolunteerData)实体类
 *
 * @author liujing
 * @since 2021-05-23 20:25:42
 */
@Data
public class VolunteerModel implements Serializable {
    /**
     * 考生分数
     */
    private Integer score;
    /**
     * 下标
     */
    private Integer offset;
    /**
     * 查询数
     */
    private Integer limit;
}
