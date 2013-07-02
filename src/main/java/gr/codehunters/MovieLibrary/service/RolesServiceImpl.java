package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.dao.SecurityRolesDAO;
import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntityDBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rolesService")
public class RolesServiceImpl {
  @Autowired
 	private SecurityRolesDAO securityRolesDAO;


  public List<SecurityRoleEntityDBImpl> listRoles() {
    return securityRolesDAO.listRoles();
  }

  public SecurityRoleEntityDBImpl loadRoleById(Long id) {
    return securityRolesDAO.findRoleById(id);  //To change body of created methods use File | Settings | File Templates.
  }
}
