package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.model.db.users.SecurityRoleEntityDBImpl;

import java.util.List;

public interface SecurityRolesDAO {
	void save(SecurityRoleEntityDBImpl role);
	void update(SecurityRoleEntityDBImpl role);
	void delete(SecurityRoleEntityDBImpl role);
  List<SecurityRoleEntityDBImpl> findRoleByName(String... roleName);
	List<SecurityRoleEntityDBImpl> listRoles();
  List<SecurityRoleEntityDBImpl> findRoleById(Long... id);
  List<String> listRoleNames();

}
