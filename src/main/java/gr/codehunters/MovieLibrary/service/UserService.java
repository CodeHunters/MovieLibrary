package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.exceptions.UserNotFoundException;
import gr.codehunters.MovieLibrary.model.UserEntity;
import gr.codehunters.MovieLibrary.model.db.users.UserEntityDBImpl;
import gr.codehunters.MovieLibrary.model.dto.users.AddressEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.AddressEntityListDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.PasswordEntityDTO;
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
  public UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl> getUserById(Long id) throws UserNotFoundException;
  public UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl> save(UserEntityDTOImpl user);
  public UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl> update(UserEntityDTOImpl user);
  public Set<UserEntityDTOImpl> loadUsers();
  public void deleteUserById(Long id);
  public Map<String,String> getGenderList();
  public PasswordEntityDTO getPasswordEntityrByUserId(long id) throws UserNotFoundException;
  public boolean checkUserPassword(Long id,String oldPassword);
  public AddressEntityListDTOImpl save(AddressEntityListDTOImpl addressEntityListDTO) throws UserNotFoundException;
  public AddressEntityListDTOImpl getAddressEntityList(String id) throws UserNotFoundException;
  public PasswordEntityDTO save(PasswordEntityDTO passwordEntity) throws UserNotFoundException;
  public AddressEntityListDTOImpl addNewAdress(AddressEntityListDTOImpl addressEntityListDTO);
}
