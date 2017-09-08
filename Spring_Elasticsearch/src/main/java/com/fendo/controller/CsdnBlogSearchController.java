package com.fendo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fendo.common.Page;
import com.fendo.entity.CsdnBlog;
import com.fendo.service.CsdnBlogService;

/**
 *  文章控制器
 * @author fendo
 *
 */
@Controller
@RequestMapping("/")
public class CsdnBlogSearchController {

	@Autowired
	CsdnBlogService csdnBlogService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public ModelAndView searchAll(String param,Integer pageSize,Integer pageNo,String type) throws Exception {
		Page page= csdnBlogService.search(param, pageSize, pageNo,type);
		ModelAndView mv = new ModelAndView();
		mv.addObject("page", page);
		mv.setViewName("index");
		return mv;
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/searchTitile")
	public ModelAndView searchTitile(String query) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("search");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search/create")
	public ModelAndView createInitialData() {
		ModelAndView mv = new ModelAndView("forward:/");
		mv.addObject("message", "文章索引已创建成功!");
		return mv;
	}
	
}
