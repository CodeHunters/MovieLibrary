package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.dao.MoviesDAO;
import gr.codehunters.MovieLibrary.model.db.movies.MovieEntityDBImpl;
import gr.codehunters.MovieLibrary.model.dto.movies.MovieEntityDTOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("moviesService")
public class MoviesServiceImpl implements MoviesService {
	
	private static final Logger logger = LoggerFactory.getLogger(MoviesServiceImpl.class);
	
  @Autowired
  MoviesDAO moviesDAO;
	
	public MovieEntityDTOImpl findEntity(String id) {
		if("new".equals(id) || id==null) {
			return new MovieEntityDTOImpl();
		} else {
			return moviesDAO.findById(new Long(id)).createImp();
		}
	}

	public MovieEntityDTOImpl save(MovieEntityDTOImpl entity) {
    MovieEntityDBImpl movieEntityDB=entity.createImp();
    moviesDAO.save(movieEntityDB);
		logger.info("entity saved: ");
		return movieEntityDB.createImp();

	}
	
	public boolean delete(String entityId) {
    MovieEntityDTOImpl entity = moviesDAO.findById(new Long(entityId)).createImp();
		if(entity==null) {
			return false;
		}
		moviesDAO.delete(entity.createImp());
		return true;
	}

}
