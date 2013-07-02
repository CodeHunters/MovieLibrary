package gr.codehunters.MovieLibrary.dao;

import java.util.Set;

import gr.codehunters.MovieLibrary.model.users.UserEntityDBImpl;

public interface UserDAO {
	void save(UserEntityDBImpl user);
	void update(UserEntityDBImpl user);
	void delete(UserEntityDBImpl user);
	UserEntityDBImpl findByUserName(String userName);
  Set<UserEntityDBImpl> listUser(Integer firstIndex, Integer size, String shortingColumn, Boolean asc);
  Set<UserEntityDBImpl> listUser();
  long countUsers();
  UserEntityDBImpl findById(int id);
}
