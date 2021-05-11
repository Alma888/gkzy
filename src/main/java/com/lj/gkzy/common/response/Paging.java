
/*
 * Copyright (c) 2021 liujing.com
 * All rights reserved.
 *
 */
package com.lj.gkzy.common.response;

/**
 * 在这里编写类的功能描述
 *
 * @author liujing
 * @created 2021/5/5
 */
public class Paging {
    private int offset;
    private int limit;
    private int total;
    private boolean hasMore;

    public Paging() {
    }

    public Paging(int offset, int limit, int total) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.hasMore = offset + limit < total;
    }

    public Paging(int offset, int limit, int total, boolean hasMore) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.hasMore = hasMore;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
        this.hasMore = offset + this.limit < this.total;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
        this.hasMore = this.offset + limit < this.total;
    }

    public int getTotal() {
        return this.total;
    }

    public boolean isHasMore() {
        return this.hasMore;
    }

    public void setTotal(int total) {
        this.total = total;
        this.hasMore = this.offset + this.limit < total;
    }
}