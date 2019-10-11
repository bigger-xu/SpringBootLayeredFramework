package com.cto.freemarker.controller;

import com.cto.freemarker.entity.Menu;
import com.cto.freemarker.entity.Test;
import com.cto.freemarker.solr.SolrService;
import com.cto.freemarker.utils.Result;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Zhangyongwei
 */
@RestController
@RequestMapping
public class IndexController{
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SolrService solrService;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Object test() {
        /*Test test = new Test();
        test.setUuid("23131231231389128322");
        test.setTitle("测试标题");
        test.setKeywords("我是关键字");
        test.setSaveTime(new Date());
        solrService.add(test);*/
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","keyWords:i OR title:i");
        String list = solrService.testList(solrQuery);
        logger.info("列表数据为:{}",list);
        return Result.ok("认证");
    }
}
