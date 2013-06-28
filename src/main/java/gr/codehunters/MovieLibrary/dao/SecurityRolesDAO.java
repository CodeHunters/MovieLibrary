package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntity;
import java.util.List;

public interface SecurityRolesDAO {
	void save(SecurityRoleEntity role);
	void update(SecurityRoleEntity role);
	void delete(SecurityRoleEntity role);
	SecurityRoleEntity findRoleByName(String roleName);
	List<SecurityRoleEntity> listRoles();
}
