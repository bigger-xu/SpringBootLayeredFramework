package com.efreight.easy.service;

import java.util.List;

import com.efreight.easy.entity.SystemLog;

import org.springframework.data.elasticsearch.core.SearchHit;

/**
 * @author ZhangYongWei
 * @since 2024/10/25
 */
public interface SystemLogEsService {
    
    //保存和修改
    void save(SystemLog article);
    //查询id
    SystemLog findById(Long id);
    //删除指定ID数据
    void   deleteById(Long id);
    
    long count();
    
    boolean existsById(Long id);
    
    List<SearchHit<SystemLog>> findByTitleOrContent(String title, String content);
}
