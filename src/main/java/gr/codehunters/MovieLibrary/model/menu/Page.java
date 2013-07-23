package gr.codehunters.MovieLibrary.model.menu;

import org.apache.commons.lang3.StringUtils;

public class Page {
  private String parentName;
  private String url;
  private String name;

  public Page(String name, String parentName, String url) {
    if (name==null || url==null || name.trim().length()==0 || url.trim().length()==0){
      throw new IllegalArgumentException("Page name and URL may not be null or empty");
    }
    this.name = StringUtils.capitalize(name);
    if (parentName!=null && parentName.trim().length()>0){
      this.parentName = StringUtils.capitalize(parentName);
    }else{
      this.parentName=name;
    }
    this.url = StringUtils.strip(url,"//");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isRoot(){
    return this.getName().compareToIgnoreCase(this.getParentName())==0;
  }
}
