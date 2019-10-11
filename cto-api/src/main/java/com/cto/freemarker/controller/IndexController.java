package com.cto.freemarker.controller;

import com.cto.freemarker.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhangyongwei
 */
@RestController
@RequestMapping
public class IndexController{
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Object test() {
        return Result.ok("认证");
    }
}
