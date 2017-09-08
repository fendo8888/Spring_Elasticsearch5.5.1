package com.fendo.service;

import java.util.List;

import com.fendo.common.Page;
import com.fendo.entity.CsdnBlog;

public interface CsdnBlogService {

	Page search(String param,Integer pageSize,Integer pageNo,String type) throws Exception ;
}
