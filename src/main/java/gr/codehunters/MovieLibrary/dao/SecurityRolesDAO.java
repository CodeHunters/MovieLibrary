package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntityDBImpl;

import java.util.List;

public interface SecurityRolesDAO {
	void save(SecurityRoleEntityDBImpl role);
	void update(SecurityRoleEntityDBImpl role);
	void delete(SecurityRoleEntityDBImpl role);
	SecurityRoleEntityDBImpl findRoleByName(String roleName);
	List<SecurityRoleEntityDBImpl> listRoles();
  SecurityRoleEntityDBImpl findRoleById(Long id);
}
