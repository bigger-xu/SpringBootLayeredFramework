package com.efreight.easy.service.impl;

import java.util.List;

import com.efreight.easy.entity.SystemLog;
import com.efreight.easy.mapper.SystemLogRepository;
import com.efreight.easy.service.SystemLogEsService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYongWei
 * @since 2024/10/25
 */
@Service
@RequiredArgsConstructor
public class SystemLogEsServiceImpl implements SystemLogEsService {
    
    private final SystemLogRepository systemLogRepository;
    
    
    @Override
    public void save(SystemLog systemLog) {
        systemLogRepository.save(systemLog);
    }
    
    @Override
    public SystemLog findById(Long id) {
        return systemLogRepository.findById(id).orElse(new SystemLog());
    }
    
    @Override
    public void deleteById(Long id) {
        systemLogRepository.deleteById(id);
    }
    
    @Override
    public long count() {
        return systemLogRepository.count();
    }
    
    @Override
    public boolean existsById(Long id) {
        return systemLogRepository.existsById(id);
    }
    
    @Override
    public List<SearchHit<SystemLog>> findByTitleOrContent(String title, String content) {
        return systemLogRepository.findByTitleOrContent(title,content);
    }
}
