package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntityDBImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository("securityRolesDao")
public class SecurityRolesDAOImpl extends CustomHibernateDaoSupport implements SecurityRolesDAO {

	@Override
	public void save(SecurityRoleEntityDBImpl role) {
		getHibernateTemplate().save(role);	
	}

	@Override
	public void update(SecurityRoleEntityDBImpl role) {
		getHibernateTemplate().update(role);	
	}

	@Override
	public void delete(SecurityRoleEntityDBImpl role) {
		getHibernateTemplate().delete(role);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SecurityRoleEntityDBImpl findRoleByName(String roleName) {
		List find = getHibernateTemplate().find(
                "from security_role_entity where roleName=?",roleName
           );
		List<SecurityRoleEntityDBImpl> roles = find;
		return roles.get(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SecurityRoleEntityDBImpl findRoleById(Long id) {
		List find = getHibernateTemplate().find("from security_role_entity where security_role_id=?",id);
		List<SecurityRoleEntityDBImpl> roles = find;
		return roles.get(0);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<SecurityRoleEntityDBImpl> listRoles() {
		List find = getHibernateTemplate().loadAll(SecurityRoleEntityDBImpl.class);
		List<SecurityRoleEntityDBImpl> roles = find;
		return roles;
	}


}
