package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.dao.UserDAO;
import gr.codehunters.MovieLibrary.exceptions.UserNotFoundException;
import gr.codehunters.MovieLibrary.model.db.users.AddressEntityDBImpl;
import gr.codehunters.MovieLibrary.model.db.users.UserEntityDBImpl;
import gr.codehunters.MovieLibrary.model.dto.users.AddressEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.AddressEntityListDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.PasswordEntityDTO;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
  @Autowired
  private UserDAO userDao;
  @Autowired
  private Assembler assembler;
  @Autowired
  private RolesService rolesService;
  private static PasswordEncoder encoder = new Md5PasswordEncoder();


  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    UserEntityDBImpl userEntity = userDao.findByUserName(username);
    if (userEntity == null) {
      throw new UsernameNotFoundException("user not found");
    }
    return assembler.buildUserFromUserEntity(userEntity);
  }

  public UserEntityDTOImpl getUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    return userDao.findByUserName(username).createImp();
  }

  public UserEntityDTOImpl getUserById(Long id) throws UserNotFoundException {
    UserEntityDBImpl userEntityDB=userDao.findById(id);
    if (userEntityDB!=null){
    return userEntityDB.createImp();
    }else {
      throw new UserNotFoundException();
    }
  }

  public UserEntityDTOImpl save(UserEntityDTOImpl user) {
    if (user.getId() != null && user.getId() > 0) {
      update(user);
    } else {
      UserEntityDBImpl userEntityDB = user.createImp();
      userEntityDB.getUserSecurityRoleEntity().addAll(rolesService.loadRoles(user.getUserSecurityRoleEntity()));

      userDao.save(userEntityDB);
      user = userEntityDB.createImp();
    }
    return user;
  }

  public UserEntityDTOImpl update(UserEntityDTOImpl user) {
    UserEntityDBImpl db_user = userDao.findById(user.getId());
    db_user.resynch(user);

    db_user.getUserAddress().clear();
    Set<AddressEntityDBImpl> addressEntityDBs = new HashSet<AddressEntityDBImpl>();
    for (AddressEntityDTOImpl addressEntityDTO : user.getUserAddress()) {
      addressEntityDBs.add(addressEntityDTO.createImp());
    }
    db_user.setUserAddress(addressEntityDBs);

    db_user.getUserSecurityRoleEntity().clear();
    db_user.getUserSecurityRoleEntity().addAll(rolesService.loadRoles(user.getUserSecurityRoleEntity()));
    userDao.update(db_user);
    return user;
  }

  public Set<UserEntityDTOImpl> loadUsers() {
    Set<UserEntityDBImpl> userEntityDBs = userDao.listUser();
    Set<UserEntityDTOImpl> userEntityDTOs = new HashSet<UserEntityDTOImpl>();
    for (UserEntityDBImpl userEntityDB : userEntityDBs) {
      userEntityDTOs.add(userEntityDB.createImp());
    }
    return userEntityDTOs;
  }

  public void deleteUserById(Long id) {
    userDao.deleteUserById(id);
  }

  public Map<String, String> getGenderList() {
    Map<String, String> genderList = new LinkedHashMap<String, String>();
    genderList.put("Male", "Male");
    genderList.put("Female", "Female");
    return genderList;
  }

  public PasswordEntityDTO getPasswordEntityrByUserId(long id) throws UserNotFoundException {
    UserEntityDTOImpl userEntityDTO = getUserById(id);
    return new PasswordEntityDTO(userEntityDTO.getId(), userEntityDTO.getUserName());
  }

  @Override
  public boolean checkUserPassword(Long id, String password) {
    return userDao.findById(id).getPassword().compareTo(encrypt(password)) == 0;
  }

  @Override
  public AddressEntityListDTOImpl save(AddressEntityListDTOImpl addressEntityListDTO) throws UserNotFoundException {
    List<AddressEntityDTOImpl> addressEntityDTOs = addressEntityListDTO.getAddressEntityDTOs();
    if (null != addressEntityDTOs && addressEntityDTOs.size() > 0) {
      long userId = addressEntityListDTO.getId();
      UserEntityDTOImpl userEntityDTO = getUserById(userId);
      userEntityDTO.setUserAddress(new HashSet<AddressEntityDTOImpl>(addressEntityListDTO.getAddressEntityDTOs()));
      save(userEntityDTO);
    }
    return addressEntityListDTO;
  }

  public PasswordEntityDTO save(PasswordEntityDTO passwordEntity) throws UserNotFoundException {
    UserEntityDTOImpl userEntityDTO = getUserById(passwordEntity.getId());
    userEntityDTO.setPassword(passwordEntity.getPassword());
    userEntityDTO.setPassword_verify(passwordEntity.getPassword());
    save(userEntityDTO);
    return passwordEntity;
  }

  private String encrypt(String password) {
    return encoder.encodePassword(password, null);
  }

  public AddressEntityListDTOImpl getAddressEntityList(String id) throws UserNotFoundException {
    AddressEntityListDTOImpl addressEntityListDTO = new AddressEntityListDTOImpl();
    addressEntityListDTO.setAddressEntityDTOs(getUserById(Long.parseLong(id)).getUserAddress());
    if (addressEntityListDTO.getAddressEntityDTOs().isEmpty()) {
      addNewAdress(addressEntityListDTO);
    }
    addressEntityListDTO.setId(Long.parseLong(id));
    return addressEntityListDTO;
  }

  public AddressEntityListDTOImpl addNewAdress(AddressEntityListDTOImpl addressEntityListDTO) {
    AddressEntityDTOImpl addressEntityDTO = new AddressEntityDTOImpl();
    addressEntityDTO.setPersonId(addressEntityListDTO.getId());
    addressEntityListDTO.getAddressEntityDTOs().add(addressEntityDTO);
    return addressEntityListDTO;
  }
}