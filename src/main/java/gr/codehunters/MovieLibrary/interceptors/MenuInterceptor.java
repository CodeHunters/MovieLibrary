/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.interceptors;

import gr.codehunters.MovieLibrary.model.menu.Page;
import gr.codehunters.MovieLibrary.service.PageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuInterceptor extends HandlerInterceptorAdapter {
  @Autowired
  PageService pageService;
  private UrlPathHelper urlPathHelper = new UrlPathHelper();
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    List<Page> pages=new ArrayList<Page>();
    String uri=StringUtils.strip(urlPathHelper.getPathWithinApplication(request), "/");


    modelAndView.addObject("userName",request.getRemoteUser());
    Page page=pageService.getByURI(uri);
    String curURI=request.getQueryString()!=null?"/"+page.getUrl()+"?"+request.getQueryString():"/"+page.getUrl();
    Page curPage=new Page(page.getName(),page.getParentName(),curURI);
    pages.add(curPage);
    while (!page.isRoot()){
      page=pageService.getByName(page.getParentName());
      pages.add(page);
    }
    Collections.reverse(pages);
    modelAndView.addObject("menuItems", pages);
    super.postHandle(request, response, handler, modelAndView);
  }

}