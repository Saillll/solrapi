package com.zzj.solrapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
//data-config中导入的entity名字
@SolrDocument(solrCoreName = "article")
public class Article extends BaseEntity{

    @Id
    @Field(value = "id")
    private String id;
    @Field(value = "title")
    private String title;
    @Field(value = "content")
    private String content;
    @Field(value = "createdate")
    private Date createdate;
    @Field(value = "editdate")
    private Date editdate;
    @Field(value = "likenums")
    private int likenums;
    @Field(value = "viewnums")
    private int viewnums;

}
