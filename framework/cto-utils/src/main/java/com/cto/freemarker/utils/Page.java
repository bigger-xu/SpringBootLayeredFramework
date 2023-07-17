package com.cto.freemarker.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @date 2019-03-22
 */
public class Page<T> {
    /**
     * 总共的页数
     */
    private int totalPage=1;

    /**
     * 总共有多少记录
     */
    private long total;

    /**
     * 目前的页数
     */
    private int pageNum;

    /**
     * 每页显示条目数
     */
    private int pageSize;

    /**
     * 要排序的字段
     */
    private String orderBy;

    /**
     * 开始的行号
     */
    private int startIndex;

    /**
     * 数据
     */
    protected List<T> rows = new ArrayList<>();

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * 根据page和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始
     *
     * @return
     */
    public int getStartIndex() {
        return ((pageNum - 1) * pageSize) + 1;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 分页信息Java类的构造函数
     *
     * @param total 总记录数
     * @param pageSize 每页显示条目数
     */
    public Page(long total, int pageSize, int page) {

        if (total > 0) {
            this.total = total;
        }
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
        if (total > 0 && pageSize > 0) {
            this.totalPage = (int) ((total + pageSize - 1) / pageSize);
        }
        setPage(page);

    }

    /**
     * 获得当前页的前一页，如果当前页是第一页，返回当前页。
     *
     * @return 当前页的前一页
     */
    public int getPreviousPage() {
        if (this.pageNum - 1 <= 0) {
            return 1;
        } else {
            return this.pageNum - 1;
        }
    }

    /**
     * 获得当前页的下一页，如果当前页是最后一页，返回当前页。
     *
     * @return 当前页的下一页
     */
    public int getNextPage() {
        if (this.pageNum + 1 >= totalPage) {
            return totalPage;
        } else {
            return this.pageNum + 1;
        }
    }

    /**
     * 分页查询的起始位置
     *
     * @return 分页查询的起始位置
     */
    public int getFirstItemPos() {
        int temp = (pageNum - 1) * pageSize;
        return temp < 0 ? 0 : temp;
    }

    /**
     * 需要从起始位置开始向后查询的总记录条数，由于分页原因，最后一页记录数不一定是每页显示的数目，可能比这小。
     *
     * @return 需要从起始位置开始向后查询的总记录条数
     */
    public long getMaxItemNum() {
        long maxItemNum = 0;
        if (total <= pageSize) {
            maxItemNum = total;
        } else if ((total - (pageNum - 1) * pageSize) >= pageSize) {
            maxItemNum = pageSize;
        } else {
            maxItemNum = total - (pageNum - 1) * pageSize;
        }
        return maxItemNum;
    }

    /**
     * 获取当前分页最后一条数据的序号
     *
     * @return long
     */
    public long getEndItemPos() {
        return this.getFirstItemPos() + this.getMaxItemNum();
    }

    /**
     *
     * 函数名称: getPage 函数功能描述: 获取页号
     *
     * @return //TODO <描述该参数的含义>
     */
    public int getPage() {
        return pageNum;
    }

    /**
     * 设置当前页，如果设置的页数大于总页数，则当前页为最后一页，如果设置页数小于0，则当前页为1。
     *
     * @param page 要设置的当前页
     */
    public void setPage(int page) {
        if (page > totalPage) {
            this.pageNum = totalPage;
        } else if (page <= 0) {
            this.pageNum = 1;
        } else {
            this.pageNum = page;
        }
    }

    /**
     *
     * 函数名称: getPageSize 函数功能描述:获取页面数据大小
     *
     * @return int
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 获得记录数
     *
     * @return 总记录数
     */
    public long gettotal() {
        return total;
    }

    /**
     * 获得总页数
     *
     * @return 总页数
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 获得要排序的字段
     *
     * @return 返回排序字段。
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置要排序的字段
     *
     * @param orderBy 要设置的排序字段。
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
