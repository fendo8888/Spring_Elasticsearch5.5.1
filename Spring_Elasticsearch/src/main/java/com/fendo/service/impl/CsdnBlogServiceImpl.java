package com.fendo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fendo.common.Page;
import com.fendo.entity.CsdnBlog;
import com.fendo.service.CsdnBlogService;

import net.sf.json.JSONObject;



@Service
public class CsdnBlogServiceImpl implements CsdnBlogService{

	@Autowired
	private TransportClient transportClient;
	
	private static int num = 0;
	private static final String IndexName="csdnblog";
	private static final String TypeName="article";
	
	public static final Logger LOGGER = Logger.getLogger(CsdnBlogServiceImpl.class);
	private static ObjectMapper mapper = new ObjectMapper(); 
	
	@Override
	public Page search(String param,Integer pageSize,Integer pageNo,String type) throws Exception {
		
		 QueryBuilder queryBuilders=null;
		 
		 Page page=new Page();
		 param = null==param?"":param;
		 pageNo = null==pageNo?1:pageNo;
		 pageSize = null==pageSize?15:pageSize;
		 page.setPageNo(pageNo);
		 page.setPageSize(pageSize);
		 
		 long start = System.currentTimeMillis();

		 HighlightBuilder hiBuilder=new HighlightBuilder().field("*").requireFieldMatch(false);  
         hiBuilder.preTags("<span style=\"color:red\">");  
         hiBuilder.postTags("</span>");  
          
		 
		 //查询所有
		 if("All".equals(type)){
			 if(!"".equals(param)&&param!=null){
				 queryBuilders = QueryBuilders.queryStringQuery(param);
				 //设置高亮显示
//				 hiBuilder=new HighlightBuilder().field("*").requireFieldMatch(false);;  
//				 hiBuilder.preTags("<span style=\"color:red\">");  
//				 hiBuilder.postTags("</span>");
				 
				//搜索title和orperator和detail
		        //QueryStringQueryBuilder queryBuilder = new QueryStringQueryBuilder(param);
		        hiBuilder.field("titles").field("content");
		        
			 }else{
				 queryBuilders = QueryBuilders.matchAllQuery();
			 }
		 }
		 //查询标题
		 if("title".equals(type)){
			 queryBuilders = QueryBuilders.fuzzyQuery("titles", param);
			 hiBuilder.field("titles");
		 }
		 //查询内容
		 if("content".equals(type)){
			 queryBuilders = QueryBuilders.fuzzyQuery("content", param);
			 hiBuilder.field("content");
		 }
		 
		 hiBuilder.fragmentSize(10000);//高亮内容长度
		 hiBuilder.requireFieldMatch(false);

		 SearchResponse response = transportClient.prepareSearch(IndexName)  
	                .setQuery(queryBuilders)  
	                .setFrom((pageNo - 1) * pageSize)   
	                .setSize(pageSize)
	                .highlighter(hiBuilder)
	        		//高亮字段前缀
	                .setExplain(true)// 设置是否按查询匹配度排序
	                .execute().actionGet(); 
		 
		 SearchHits hits = response.getHits();
		 
		 int total=(int) hits.getTotalHits();
		 
		 page.setTotalRecord(total); //总记录数
		 
		 int totalPage = total/pageSize;
		 if(total % pageSize != 0){
		     totalPage += 1;
		 }
		 
		 page.setTotalPage(totalPage);//总页数
		 
		 for (SearchHit searchHit : hits) {
			   

				Map source = searchHit.getSource();
				CsdnBlog entity = (CsdnBlog) JSONObject
					.toBean(JSONObject.fromObject(source) , CsdnBlog.class);
				
			    
			    if(!"".equals(type)&&type!=null){
			    	
				    String json=searchHit.getSourceAsString();
				    CsdnBlog csdnblog=mapper.readValue(json,CsdnBlog.class);
				    
				    //获取高亮域
				    Map<String,HighlightField> result=searchHit.getHighlightFields();
				    
				    
					 //查询标题
					 if("title".equals(type)){
						    //从高亮域中取得指定域
							HighlightField nameField=result.get("titles");
							//取得定于的高亮标签
							Text[] nameTexts=nameField.getFragments();
						    
							//为name串值增加自定义的高亮标签
							String titles="";
							for(Text text:nameTexts){
								titles+=text;
							}

							//将追加了高亮标签的串值重新填充到对应的对象
							entity.setTitles(titles);
					 }
					 //查询内容
					 if("content".equals(type)){
						    //从高亮域中取得指定域
							HighlightField nameField=result.get("content");
							//取得定于的高亮标签
							Text[] contentsTexts=nameField.getFragments();
						    
							//为name串值增加自定义的高亮标签
							String contents="";
							for(Text text:contentsTexts){
								contents+=text;
							}

							//将追加了高亮标签的串值重新填充到对应的对象
							entity.setContent(contents);
					 }
					 
					 //查询所有
					 if("All".equals(type)){
						    //从高亮域中取得指定域
							HighlightField titlesField=result.get("titles");
							if(titlesField!=null&&!"".equals(titlesField)){
								//取得定于的高亮标签
								Text[] nameTexts=titlesField.getFragments();
							    
								//为name串值增加自定义的高亮标签
								String titles="";
								for(Text text:nameTexts){
									titles+=text;
								}
								entity.setTitles(titles);
							}
							
							//从高亮域中取得指定域
							HighlightField contentField=result.get("content");
							if(!"".equals(contentField)&&contentField!=null){
								//取得定于的高亮标签
								Text[] contentsTexts=contentField.getFragments();
								//为name串值增加自定义的高亮标签
								String contents="";
								for(Text text:contentsTexts){
									contents+=text;
								}
								
								entity.setContent(contents);
							}

					 }
				    
					
			    }

				page.getSimpleResult().add(entity);
				
				
			}
		 long end = System.currentTimeMillis();
		 
		 SearchResponse responses = transportClient.prepareSearch(IndexName)  
	                .setQuery(QueryBuilders.matchAllQuery())  
	                .execute().actionGet(); 
		 
		 SearchHits hi = responses.getHits();
		 
		 int totals=(int) hi.getTotalHits();
		 
		 page.setMessage("在" + totals + "条记录中,搜索("+param+"),共用时间 -->> " + (end - start) + " 毫秒");
		 System.out.println("在" + totals + "条记录中,搜索"+param+",共用时间 -->> " + (end - start) + " 毫秒");
		
		 return page;
		
	}

}
