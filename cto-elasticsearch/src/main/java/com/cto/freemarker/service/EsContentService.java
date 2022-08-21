package com.cto.freemarker.service;


import com.cto.freemarker.dao.ContentEsRepository;
import com.cto.freemarker.entity.EsContent;
import com.cto.freemarker.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/9/1 22:05
 */
@Service
public class EsContentService {

    private static final Integer PAGE_SIZE = 10;
    private final ContentEsRepository contentEsRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 构造方法注入
     * @param contentEsRepository
     * @param elasticsearchRestTemplate
     */
    public EsContentService(ContentEsRepository contentEsRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.contentEsRepository = contentEsRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    /**
     * 单个保存方法
     * @param esContent
     */
    public void save(EsContent esContent) {
        contentEsRepository.save(esContent);
    }

    /**
     * 批量保存方法
     * @param list
     */
    public void saveAll(List<EsContent> list) {
        contentEsRepository.saveAll(list);
    }

    /**
     * 从es检索数据
     *
     * @param content  搜索关键字
     * @param pageNum  页
     * @param pageSize 条
     * @return
     */
    public AggregatedPage<EsContent> getIdeaListBySrt(String content, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        String preTag = "<font color='#dd4b39'>";
        String postTag = "</font>";

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(content,"title","keywords"))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags(preTag).postTags(postTag),
                        new HighlightBuilder.Field("keywords").preTags(preTag).postTags(postTag))
                .build();
        searchQuery.setPageable(pageable);

        // 高亮字段
       /* AggregatedPage<EsContent> ideas = elasticsearchRestTemplate.queryForPage(searchQuery, EsContent.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<EsContent> EsContentList = new ArrayList<>();
                //命中记录
                SearchHits hits = response.getHits();
                for (SearchHit hit : hits){
                    if (hits.totalHits <= 0){
                        return null;
                    }
                    //设置高亮（若对应字段有高亮的话）
                    EsContent EsContent = mapSearchHit(hit, EsContent.class);
                    EsContentList.add(EsContent);
                }
                return new AggregatedPageImpl<>((List<T>)EsContentList);
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(Long.valueOf(String.valueOf(searchHit.getSourceAsMap().get("createTime"))));
                EsContent EsContent = new EsContent(Long.valueOf(searchHit.getId()),
                        String.valueOf(searchHit.getSourceAsMap().get("uuid")),
                        String.valueOf(searchHit.getSourceAsMap().get("channelId")),
                        String.valueOf(searchHit.getSourceAsMap().get("catUuid")),
                        String.valueOf(searchHit.getSourceAsMap().get("title")),
                        String.valueOf(searchHit.getSourceAsMap().get("keywords")),
                        String.valueOf(searchHit.getSourceAsMap().get("categoryName")),
                        String.valueOf(searchHit.getSourceAsMap().get("nickName")),
                        cal.getTime(),
                        String.valueOf(searchHit.getSourceAsMap().get("clickCount")));
                //获取高亮
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                HighlightField highlightFieldTitle = highlightFields.get("title");
                HighlightField highlightFieldKeywords = highlightFields.get("keywords");
                if (highlightFieldTitle != null){
                    String highLightTitle = highlightFieldTitle.fragments()[0].toString();
                    EsContent.setTitle(highLightTitle);
                }
                if (highlightFieldKeywords != null){
                    String highLightKeywords = highlightFieldKeywords.fragments()[0].toString();
                    EsContent.setKeywords(highLightKeywords);
                }
                return (T) EsContent;
            }
        });*/
        return null;
    }

    /**
     * 更具参数返回自己的page
     * @param k
     * @param p
     * @return
     */
    public com.cto.freemarker.utils.Page<EsContent> getPage(String k, Integer p) throws IOException {
        if(StringUtils.isEmpty(k)){
            k="";
        }
        SearchRequest searchRequest = new SearchRequest("594cto_content");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().trackTotalHits(true);
        MultiMatchQueryBuilder termQueryBuilder = QueryBuilders.multiMatchQuery(k,"title","keywords");
        termQueryBuilder.type(MultiMatchQueryBuilder.Type.CROSS_FIELDS);
        termQueryBuilder.analyzer("ik_max_word");
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.from(p);
        searchSourceBuilder.size(PAGE_SIZE);
        searchSourceBuilder.sort("createTime", SortOrder.DESC);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title").fragmentSize(255);
        highlightBuilder.field("keywords").fragmentSize(255);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = elasticsearchRestTemplate.getClient().search(searchRequest, RequestOptions.DEFAULT);
        List<EsContent> list = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.parseLong(String.valueOf(hit.getSourceAsMap().get("createTime"))));
            EsContent esContent = new EsContent(Long.valueOf(hit.getId()),
                    String.valueOf(hit.getSourceAsMap().get("uuid")),
                    String.valueOf(hit.getSourceAsMap().get("channelId")),
                    String.valueOf(hit.getSourceAsMap().get("catUuid")),
                    String.valueOf(hit.getSourceAsMap().get("title")),
                    String.valueOf(hit.getSourceAsMap().get("keywords")),
                    String.valueOf(hit.getSourceAsMap().get("categoryName")),
                    String.valueOf(hit.getSourceAsMap().get("nickName")),
                    cal.getTime(),
                    String.valueOf(hit.getSourceAsMap().get("clickCount")));

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            HighlightField keywords = highlightFields.get("keywords");
            if(title != null){
                String n_title = "";
                for (Text text : title.getFragments()) {
                    n_title += text;
                }
                esContent.setTitle(n_title);
            }
            if(keywords != null){
                String n_keywords = "";
                for (Text text : keywords.getFragments()) {
                    n_keywords += text;
                }
                esContent.setKeywords(n_keywords);
            }
            list.add(esContent);
        }
        com.cto.freemarker.utils.Page<EsContent> contentPage = new com.cto.freemarker.utils.Page<>(response.getHits().getTotalHits(),PAGE_SIZE,p);
        contentPage.setRows(list);
        return contentPage;
    }

    /**
     * 获取转载列表
     * @param channelId
     * @param page
     * @return
     */
    public Page<EsContent> getPageByChannelId(Long channelId,Integer page) throws IOException {
        SearchRequest searchRequest = new SearchRequest("594cto_content");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().trackTotalHits(true);
        TermQueryBuilder termQuery = termQuery("channelId", channelId);
        searchSourceBuilder.query(termQuery);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.from(page);
        searchSourceBuilder.size(PAGE_SIZE);
        searchSourceBuilder.sort("createTime", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = elasticsearchRestTemplate.getClient().search(searchRequest, RequestOptions.DEFAULT);
        List<EsContent> list = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.parseLong(String.valueOf(hit.getSourceAsMap().get("createTime"))));
            EsContent esContent = new EsContent(Long.valueOf(hit.getId()),
                    String.valueOf(hit.getSourceAsMap().get("uuid")),
                    String.valueOf(hit.getSourceAsMap().get("channelId")),
                    String.valueOf(hit.getSourceAsMap().get("catUuid")),
                    String.valueOf(hit.getSourceAsMap().get("title")),
                    String.valueOf(hit.getSourceAsMap().get("keywords")),
                    String.valueOf(hit.getSourceAsMap().get("categoryName")),
                    String.valueOf(hit.getSourceAsMap().get("nickName")),
                    cal.getTime(),
                    String.valueOf(hit.getSourceAsMap().get("clickCount")));
            list.add(esContent);
        }
        com.cto.freemarker.utils.Page<EsContent> contentPage = new com.cto.freemarker.utils.Page<>(response.getHits().getTotalHits(),PAGE_SIZE,page);
        contentPage.setRows(list);
        return contentPage;
    }

    /**
     * 分类查询
     * @param uuid
     * @param page
     * @return
     */
    public Page<EsContent> getChannelUuidPage(String uuid,Integer page) throws IOException {
        SearchRequest searchRequest = new SearchRequest("594cto_content");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().trackTotalHits(true);
        TermQueryBuilder termQuery = termQuery("catUuid", uuid);
        searchSourceBuilder.query(termQuery);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.from(page);
        searchSourceBuilder.size(PAGE_SIZE);
        searchSourceBuilder.sort("createTime", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = elasticsearchRestTemplate.getClient().search(searchRequest, RequestOptions.DEFAULT);
        List<EsContent> list = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.parseLong(String.valueOf(hit.getSourceAsMap().get("createTime"))));
            EsContent esContent = new EsContent(Long.valueOf(hit.getId()),
                    String.valueOf(hit.getSourceAsMap().get("uuid")),
                    String.valueOf(hit.getSourceAsMap().get("channelId")),
                    String.valueOf(hit.getSourceAsMap().get("catUuid")),
                    String.valueOf(hit.getSourceAsMap().get("title")),
                    String.valueOf(hit.getSourceAsMap().get("keywords")),
                    String.valueOf(hit.getSourceAsMap().get("categoryName")),
                    String.valueOf(hit.getSourceAsMap().get("nickName")),
                    cal.getTime(),
                    String.valueOf(hit.getSourceAsMap().get("clickCount")));
            list.add(esContent);
        }
        com.cto.freemarker.utils.Page<EsContent> contentPage = new com.cto.freemarker.utils.Page<>(response.getHits().getTotalHits(),PAGE_SIZE,page);
        contentPage.setRows(list);
        return contentPage;
    }
}
