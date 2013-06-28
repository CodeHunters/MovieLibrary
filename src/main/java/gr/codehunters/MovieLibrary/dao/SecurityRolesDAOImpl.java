package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntity;
import gr.codehunters.MovieLibrary.util.CustomHibernateDaoSupport;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository("securityRolesDao")
public class SecurityRolesDAOImpl extends CustomHibernateDaoSupport implements SecurityRolesDAO {

	@Override
	public void save(SecurityRoleEntity role) {
		getHibernateTemplate().save(role);	
	}

	@Override
	public void update(SecurityRoleEntity role) {
		getHibernateTemplate().update(role);	
	}

	@Override
	public void delete(SecurityRoleEntity role) {
		getHibernateTemplate().delete(role);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SecurityRoleEntity findRoleByName(String roleName) {
		List find = getHibernateTemplate().find(
                "from security_role_entity where roleName=?",roleName
           );
		List<SecurityRoleEntity> roles = find;
		return roles.get(0);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<SecurityRoleEntity> listRoles() {
		List find = getHibernateTemplate().loadAll(SecurityRoleEntity.class);
		List<SecurityRoleEntity> roles = find;
		return roles;
	}


}
