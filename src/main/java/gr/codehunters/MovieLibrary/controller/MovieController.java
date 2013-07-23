package gr.codehunters.MovieLibrary.controller;

import gr.codehunters.MovieLibrary.model.dto.movies.MovieEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.menu.Page;
import gr.codehunters.MovieLibrary.service.PageService;
import gr.codehunters.MovieLibrary.service.MoviesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
public class MovieController {

  private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

  @Autowired
  MoviesService moviesService;
  @Autowired
  PageService pageService;

  @PostConstruct
  public void init() {
    pageService.add(new Page("Add Movie","home","/movies/movie/new"));
  }

  @RequestMapping(value = "/movies/movie/{id}", method = RequestMethod.GET)
  public ModelAndView view(ModelAndView mv, @ModelAttribute("entity") MovieEntityDTOImpl movie) {
    mv.addObject("movie", movie);
    mv.setViewName("movie_detail");
    return mv;
  }

  @RequestMapping(value = "/movies/movie/{id}", method = RequestMethod.POST)
  public ModelAndView update(ModelAndView mv, @ModelAttribute("movie") @Valid MovieEntityDTOImpl movie, BindingResult result) {
    logger.info("updating /myentity");
    if (result.hasErrors()) {
      mv.addObject("movie", movie);
      mv.setViewName("movie_detail");
    } else {
      moviesService.save(movie);
      mv = new ModelAndView();
      mv.setViewName("movie_detail");
      mv.addObject("message", "Successfully saved movie: " + movie.getName());
    }
    return mv;
  }
}

