package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.dao.UserDAO;
import gr.codehunters.MovieLibrary.model.db.users.UserEntityDBImpl;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserService,UserDetailsService {
	@Autowired
	private UserDAO userDao;
	@Autowired
	private Assembler assembler;
  @Autowired
  private RolesService rolesService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		UserEntityDBImpl userEntity = userDao.findByUserName(username);
		if (userEntity == null){
			throw new UsernameNotFoundException("user not found");    
		}
		return assembler.buildUserFromUserEntity(userEntity);  
    }

  public UserEntityDTOImpl getUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
 		return userDao.findByUserName(username).createImp();
  }

  public UserEntityDTOImpl getUserById(Long id) {
    return userDao.findById(id).createImp();
  }

  public UserEntityDTOImpl save(UserEntityDTOImpl user) {
    if (user.getId()!=null && user.getId()>0){
       update(user);
    }else {
      UserEntityDBImpl userEntityDB=user.createImp();
      userEntityDB.getUserSecurityRoleEntity().addAll(rolesService.loadRoles(user.getUserSecurityRoleEntity()));
       userDao.save(userEntityDB);
      user=userEntityDB.createImp();
    }
    return user;
  }

  public UserEntityDTOImpl update(UserEntityDTOImpl user) {
    UserEntityDBImpl db_user=userDao.findById(user.getId());
    db_user.resynch(user);

    db_user.getUserAddress().clear();
    db_user.getUserSecurityRoleEntity().clear();
    db_user.getUserSecurityRoleEntity().addAll(rolesService.loadRoles(user.getUserSecurityRoleEntity()));
    userDao.update(db_user);
    return user;
  }

  public Set<UserEntityDTOImpl> loadUsers() {
    Set<UserEntityDBImpl> userEntityDBs= userDao.listUser();
    Set<UserEntityDTOImpl> userEntityDTOs=new HashSet<UserEntityDTOImpl>();
    for (UserEntityDBImpl userEntityDB : userEntityDBs) {
      userEntityDTOs.add(userEntityDB.createImp());
    }
    return userEntityDTOs;
  }

  public void deleteUserById(Long id) {
    userDao.deleteUserById(id);
  }

  public Map<String,String> getGenderList() {
    Map<String, String> genderList = new LinkedHashMap<String, String>();
    genderList.put("Male", "Male");
    genderList.put("Female", "Female");
    return genderList;
  }
}