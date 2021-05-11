
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.common.response;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;

import java.io.Serializable;

/**
 * 在这里编写类的功能描述
 *
 * @author liujing
 * @created 2021/5/5
 */
public class PagingResponse<T> implements Serializable {
    private boolean success;
    private ErrorMsg error;
    private T data;
    private Paging paging;

    public PagingResponse() {
    }

    public static <T> PagingResponse<T> buildSuccess(T data) {
        PagingResponse response = new PagingResponse();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> PagingResponse<T> buildSuccess(T data, Paging paging) {
        PagingResponse response = new PagingResponse();
        response.setSuccess(true);
        response.setData(data);
        response.setPaging(paging);
        return response;
    }

    public static <T> PagingResponse<T> buildSuccess(T data, int offset, int limit, int total) {
        PagingResponse response = new PagingResponse();
        response.setSuccess(true);
        response.setData(data);
        response.setPaging(new Paging(offset, limit, total));
        return response;
    }

    public static <T> PagingResponse<T> buildFailure() {
        PagingResponse response = new PagingResponse();
        response.setSuccess(false);
        response.setPaging(new Paging());
        return response;
    }

    public static <T> PagingResponse<T> buildFailure(String errorMsg) {
        PagingResponse response = new PagingResponse();
        response.setSuccess(false);
        response.setError(new ErrorMsg(errorMsg));
        response.setPaging(new Paging());
        return response;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrorMsg getError() {
        return this.error;
    }

    public void setError(ErrorMsg error) {
        this.error = error;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Paging getPaging() {
        return this.paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
