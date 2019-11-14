package com.itheima.domain;

import java.util.List;

public abstract class PageDao<T> {

    public abstract Integer findTotalCount() throws Exception;

    public abstract List<T> findAll(int start, int rows) throws Exception;
}
