package com.cto.freemarker.solr;

import org.apache.solr.client.solrj.SolrQuery;

import org.springframework.stereotype.Service;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @date 2019-10-11
 */
@Service
public interface SolrService<T> {

    /**
     * 添加数据
     */
    void add(T obj);

    String testList(SolrQuery solrQuery);
}
