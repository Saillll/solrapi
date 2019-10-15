package com.zzj.solrapi.controller;


import com.alibaba.fastjson.JSON;
import com.zzj.solrapi.entity.Article;
import com.zzj.solrapi.manager.SolrServerManager;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.StringUtils;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private SolrServerManager solrServerManager;

    @Value("${spring.data.solr.corename}")
    private String collectionName;



    @ResponseBody
    @GetMapping(value = "/allarticle")
    public String getAllArticle(String contentWord) throws IOException, SolrServerException {
        String word = "content:";
        contentWord = StringUtils.isEmpty(contentWord)?"*":contentWord;
        word = word + contentWord;
        SolrClient solrClient = solrServerManager.getSolrClient(collectionName);
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("q",word);
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setStart(0);
        solrQuery.setRows(2);
        solrQuery.setQuery(word);
//        SolrParams params = new MapSolrParams(parameters);
//        QueryResponse queryResponse = solrClient.query(params);
        QueryResponse queryResponse = solrClient.query(solrQuery);
        List<Article> articles = queryResponse.getBeans(Article.class);
        String articleString = JSON.toJSONString(articles);
        return articleString;
    }
    @ResponseBody
    @GetMapping(value = "/deletearticle")
    public String deletearticle(String contentWord) throws IOException, SolrServerException {
        String deleter = "content:";
        if(StringUtils.isEmpty(contentWord)){
            return "无法删除全部数据";
        }
        deleter = deleter + contentWord;
        SolrClient solrClient = solrServerManager.getSolrClient(collectionName);

        UpdateResponse updateResponse = solrClient.deleteByQuery(deleter);
        solrClient.commit();
        int status = updateResponse.getStatus();
        if(status == 0){
            System.gc();
            return "删除成功";
        }else{
            return "";
        }
    }

}
