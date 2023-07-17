package com.cto.freemarker.dao;


import com.cto.freemarker.entity.EsContent;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/9/1 22:04
 */
public interface ContentEsRepository  extends ElasticsearchRepository<EsContent,Long> {
}
