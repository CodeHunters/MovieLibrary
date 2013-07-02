package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.dao.MoviesDAO;
import gr.codehunters.MovieLibrary.model.movies.MovieEntityDBImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("moviesService")
public class MoviesServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(MoviesServiceImpl.class);
	
  @Autowired
  MoviesDAO moviesDAO;
	
	public MovieEntityDBImpl findEntity(String id) {
		if("new".equals(id)) {
			return new MovieEntityDBImpl();
		} else {
			return moviesDAO.findById(new Integer(id));
		}
	}

	public MovieEntityDBImpl save(MovieEntityDBImpl entity) {
    moviesDAO.save(entity);
		logger.info("entity saved: ");
		return entity;

	}
	
	public boolean delete(String entityId) {
		MovieEntityDBImpl entity = moviesDAO.findById(new Integer(entityId));
		if(entity==null) {
			return false;
		}
		moviesDAO.delete(entity);
		return true;
	}

}
