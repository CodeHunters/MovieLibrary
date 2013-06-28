package gr.codehunters.MovieLibrary.dao;

import java.util.Set;

import gr.codehunters.MovieLibrary.model.users.UserEntity;

public interface UserDAO {
	void save(UserEntity user);
	void update(UserEntity user);
	void delete(UserEntity user);
	UserEntity findByUserName(String userName);
  Set<UserEntity> listUser(Integer firstIndex, Integer size, String shortingColumn, Boolean asc);
  Set<UserEntity> listUser();
  long countUsers();
}
