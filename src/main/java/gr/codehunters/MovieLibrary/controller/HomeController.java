package gr.codehunters.MovieLibrary.controller;

import gr.codehunters.MovieLibrary.model.menu.Page;
import gr.codehunters.MovieLibrary.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

@Controller
public class HomeController {
  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
  @Autowired
  PageService pageService;

  @PostConstruct
  public void init() {
    pageService.add(new Page("Home", "", "/"));
  }

  @RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
  public String home() {
    logger.info("requesting home");
    return "home";
  }
}

