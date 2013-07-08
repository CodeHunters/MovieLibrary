package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.model.MovieEntity;
import gr.codehunters.MovieLibrary.model.db.movies.MovieEntityDBImpl;
import gr.codehunters.MovieLibrary.model.dto.movies.MovieEntityDTOImpl;

public interface MoviesService {
  public MovieEntity<Long,String,MovieEntityDBImpl,MovieEntityDTOImpl> findEntity(String id);
 	public  MovieEntity<Long,String,MovieEntityDBImpl,MovieEntityDTOImpl> save(MovieEntityDTOImpl entity);
 	public boolean delete(String entityId);
}