package gr.codehunters.MovieLibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.codehunters.MovieLibrary.service.EntityService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	EntityService entityService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
	    	logger.info("requesting home");
		return "home";
	}

  @RequestMapping(value="/", method=RequestMethod.GET)
 	public String defaultPage() {
 	    	logger.info("requesting home");
 		return "home";
 	}

}

