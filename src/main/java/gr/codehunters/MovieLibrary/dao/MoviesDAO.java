package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.model.db.movies.MovieEntityDBImpl;

import java.util.Set;

public interface MoviesDAO {
  void save(MovieEntityDBImpl myEntity);
 	void update(MovieEntityDBImpl myEntity);
 	void delete(MovieEntityDBImpl myEntity);
  Set<MovieEntityDBImpl> listMyMovies();
  MovieEntityDBImpl findById(long id);
}
