package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.dao.SecurityRolesDAO;
import gr.codehunters.MovieLibrary.model.db.users.SecurityRoleEntityDBImpl;
import gr.codehunters.MovieLibrary.model.dto.users.SecurityRoleEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import gr.codehunters.MovieLibrary.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("rolesService")
public class RolesServiceImpl implements RolesService {
  @Autowired
  private SecurityRolesDAO securityRolesDAO;


  public List<SecurityRoleEntityDTOImpl> listRoles() {
    List<SecurityRoleEntityDBImpl> securityRoleEntityDBs = securityRolesDAO.listRoles();
    List<SecurityRoleEntityDTOImpl> securityRoleEntityDTOs = new ArrayList<SecurityRoleEntityDTOImpl>();
    for (SecurityRoleEntityDBImpl securityRoleEntityDB : securityRoleEntityDBs) {
      securityRoleEntityDTOs.add(securityRoleEntityDB.createImp());
    }
    return securityRoleEntityDTOs;
  }

  public List<SecurityRoleEntityDTOImpl> loadRoleById(Long... id) {
    if (id.length > 0) {
      List<SecurityRoleEntityDBImpl> securityRoleEntityDBs = securityRolesDAO.findRoleById(id);
      List<SecurityRoleEntityDTOImpl> securityRoleEntityDTOs = new ArrayList<SecurityRoleEntityDTOImpl>();
      for (SecurityRoleEntityDBImpl securityRoleEntityDB : securityRoleEntityDBs) {
        securityRoleEntityDTOs.add(securityRoleEntityDB.createImp());
      }
      return securityRoleEntityDTOs;
    } else {
      return new ArrayList<SecurityRoleEntityDTOImpl>();
    }
  }

  public List<SecurityRoleEntityDTOImpl> loadRoleByName(String... roleName) {
    if (roleName.length > 0) {
      List<SecurityRoleEntityDBImpl> securityRoleEntityDBs = securityRolesDAO.findRoleByName(roleName);
      List<SecurityRoleEntityDTOImpl> securityRoleEntityDTOs = new ArrayList<SecurityRoleEntityDTOImpl>();
      for (SecurityRoleEntityDBImpl securityRoleEntityDB : securityRoleEntityDBs) {
        securityRoleEntityDTOs.add(securityRoleEntityDB.createImp());
      }
      return securityRoleEntityDTOs;
    } else {
      return new ArrayList<SecurityRoleEntityDTOImpl>();
    }
  }

  public List<String> listRoleNames() {
    return securityRolesDAO.listRoleNames();
  }

  @Override
  public List<SecurityRoleEntityDBImpl> loadRoles(Set<SecurityRoleEntityDTOImpl> userSecurityRoleEntity) {
    if (userSecurityRoleEntity == null) {
      return new ArrayList<SecurityRoleEntityDBImpl>();
    } else {
      List<SecurityRoleEntityDBImpl> securityRoleEntityDBs = new ArrayList<SecurityRoleEntityDBImpl>();
      List<Long> securityRoleEntityIDs = new ArrayList<Long>();
      for (SecurityRoleEntityDTOImpl securityRoleEntityDTO : userSecurityRoleEntity) {
        securityRoleEntityIDs.add(securityRoleEntityDTO.getId());
      }
      securityRoleEntityDBs.addAll(securityRolesDAO.findRoleById(securityRoleEntityIDs.toArray(new Long[]{0l})));
      return securityRoleEntityDBs;
    }
  }


  public List<Pair<String, Boolean>> addRoleList(UserEntityDTOImpl user) {
    List<Pair<String, Boolean>> roles = new ArrayList<Pair<String, Boolean>>();
    Set<SecurityRoleEntityDTOImpl> userRoles = user.getUserSecurityRoleEntity();
    for (SecurityRoleEntityDTOImpl role : listRoles()) {
      roles.add(new Pair<String, Boolean>(role.getRoleName(), hasRole(userRoles, role.getRoleName())));
    }
    return roles;
  }

  private boolean hasRole(Set<SecurityRoleEntityDTOImpl> userRoles, String role) {
    for (SecurityRoleEntityDTOImpl userRole : userRoles) {
      if (userRole.getRoleName().compareTo(role) == 0) return true;
    }
    return false;
  }

}
