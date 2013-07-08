/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.model.db.users.SecurityRoleEntityDBImpl;
import gr.codehunters.MovieLibrary.model.dto.users.SecurityRoleEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import gr.codehunters.MovieLibrary.util.Pair;

import java.util.List;
import java.util.Set;

public interface RolesService {
  public List<SecurityRoleEntityDTOImpl> listRoles();
  public List<SecurityRoleEntityDTOImpl> loadRoleById(Long... id);
  public List<SecurityRoleEntityDTOImpl> loadRoleByName(String... roleName);
  public List<String> listRoleNames();
  List<SecurityRoleEntityDBImpl> loadRoles(Set<SecurityRoleEntityDTOImpl> userSecurityRoleEntity);
  public List<Pair<String,Boolean>> addRoleList(UserEntityDTOImpl user);
}
