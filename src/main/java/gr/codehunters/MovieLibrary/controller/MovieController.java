package gr.codehunters.MovieLibrary.controller;

import gr.codehunters.MovieLibrary.model.dto.movies.MovieEntityDTOImpl;
import gr.codehunters.MovieLibrary.service.MoviesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
  MoviesServiceImpl moviesService;
	
	@ModelAttribute("movies")
	public MovieEntityDTOImpl init(@PathVariable String id) {
		return moviesService.findEntity(id);
	}
	
	@RequestMapping(value="/movies/movie/{id}", method=RequestMethod.GET)
	public ModelAndView view(ModelAndView mv, @ModelAttribute("entity") MovieEntityDTOImpl entity) {
		mv.addObject("entity", entity);
		mv.setViewName("entity_detail");
		logger.info("requesting /myentity");
		return mv;
	}

	@RequestMapping(value="/movies/movie/{id}", method=RequestMethod.POST)
	public String update(ModelAndView mv, @ModelAttribute("entity") MovieEntityDTOImpl entity) {
	    	logger.info("updating /myentity");
		return "redirect:/movies/movie/"+moviesService.save(entity).getId();
	}
}

