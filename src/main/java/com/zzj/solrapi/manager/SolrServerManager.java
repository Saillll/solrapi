package com.zzj.solrapi.manager;

import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Setter
//@Getter
@Component
public class SolrServerManager {

    @Value("${spring.data.solr.host}")
    private String url;
    private SolrClient solrClient;


    public SolrServerManager() {
    }

    public SolrServerManager(String url, SolrClient solrClient) {
        this.url = url;
        this.solrClient = solrClient;
    }
    public SolrClient getSolrClient(String collectionName){
        try {
            if(solrClient == null){
                solrClient = new HttpSolrClient.Builder(url+collectionName).withConnectionTimeout(6000).build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return solrClient;
    }
}
