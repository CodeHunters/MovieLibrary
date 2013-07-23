package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.model.menu.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component("pageService")
public class PageService {
  HashMap<String,Page> namedPageObjects =new HashMap<String,Page>();
  HashMap<String,Page> urlPageObjects =new HashMap<String,Page>();

  public void add(Page page) {
    namedPageObjects.put(page.getName().toLowerCase(),page);
    urlPageObjects.put(page.getUrl(),page);
  }

  public Page getByURI(String uri) {
    Page page;
    do {
      page=urlPageObjects.get(uri);
      uri=uri.substring(0,Math.max(uri.lastIndexOf("/"),0));
    }while (page==null && uri.length()>0);
    return page;
  }

  public Page getByName(String parentName) {
    return namedPageObjects.get(parentName.toLowerCase());
  }
}
