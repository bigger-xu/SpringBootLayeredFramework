package com.efreight.easy.controller;

import java.util.List;

import com.efreight.easy.entity.SystemLog;
import com.efreight.easy.service.SystemLogEsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYongWei
 * @since 2024/10/25
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/syslog")
public class SystemLogController {
    
    private final SystemLogEsService systemLogEsService;
    
    @RequestMapping("/count")
    public void count() {
        long count = systemLogEsService.count();
        log.info("count:{}", count);
    }
    
    @RequestMapping("/save")
    public void save(@RequestBody SystemLog log) {
        systemLogEsService.save(log);
    }
    
    @RequestMapping("/findById")
    public SystemLog findById(Long id) {
        SystemLog log = new SystemLog();
        return systemLogEsService.findById(id);
    }
    
    
    @RequestMapping("/findByTitleOrContent")
    public List<SearchHit<SystemLog>> findByTitleOrContent(String title, String content) {
        return systemLogEsService.findByTitleOrContent(title, content);
    }
    
    
}
