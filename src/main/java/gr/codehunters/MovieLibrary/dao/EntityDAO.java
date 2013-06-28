package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.model.MyEntity;

import java.util.Set;

public interface EntityDAO {
  void save(MyEntity myEntity);
 	void update(MyEntity myEntity);
 	void delete(MyEntity myEntity);
  Set<MyEntity> listMyEntities();

  MyEntity findById(int id);
}
