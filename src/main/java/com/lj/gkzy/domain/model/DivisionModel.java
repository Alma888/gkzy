
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区分维度的Model
 *
 * @author liujing
 * @created 2021/5/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivisionModel {
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 专业名称
     */
    private String theNameOfTheProfessional;
}

