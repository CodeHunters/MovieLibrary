package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.model.UserEntity;
import gr.codehunters.MovieLibrary.model.db.users.UserEntityDBImpl;
import gr.codehunters.MovieLibrary.model.dto.users.AddressEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.SecurityRoleEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.Set;

public interface UserService {
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;
  public UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl> getUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;
  public UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl> getUserById(Long id);
  public UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl> save(UserEntityDTOImpl user);
  public UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl> update(UserEntityDTOImpl user);
  public Set<UserEntityDTOImpl> loadUsers();
  public void deleteUserById(Long id);
  public Map<String,String> getGenderList();
}
