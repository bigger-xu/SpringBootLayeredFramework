package com.cto.freemarker.solr.impl;

import com.cto.freemarker.solr.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @date 2019-10-11
 */
@Service
public class SolrServiceImpl<T> implements SolrService<T> {
    private static final Logger logger = LoggerFactory.getLogger(SolrServiceImpl.class);
    @Autowired
    private SolrClient client;

    @Override
    public void add(T obj) {
        try {
            client.addBean(obj);
            logger.info("Solr添加成功,添加数据为:{}",obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String testList(SolrQuery solrQuery) {
        try{
            //solrQuery.setQuery("*:*");
            //solrQuery.addField("*");
            //solrQuery.add("q","id:4567");

            //solrQuery.setSort("id", SolrQuery.ORDER.asc);
            //设置查询的条数
            solrQuery.setRows(10);
            //设置查询的开始
            solrQuery.setStart(0);
            //设置高亮
            solrQuery.setHighlight(true);
            solrQuery.set("wt","json");
            solrQuery.set("fl","uuid,title,keyWords");
            //设置高亮的字段
            solrQuery.addHighlightField("keyWords");
            //设置高亮的样式
            solrQuery.setHighlightSimplePre("<font color='red'>");
            solrQuery.setHighlightSimplePost("</font>");
            System.out.println(solrQuery);
            QueryResponse response = client.query(solrQuery);
            //返回高亮显示结果
            //Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            //查询返回的结果
            //response.getResults();
            return response.getResults().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
