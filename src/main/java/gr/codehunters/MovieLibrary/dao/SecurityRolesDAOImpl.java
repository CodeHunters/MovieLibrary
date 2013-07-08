package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.model.db.users.SecurityRoleEntityDBImpl;

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
	public List<SecurityRoleEntityDBImpl> findRoleByName(String... roleNames) {
    List find = getHibernateTemplate().findByNamedParam("select sr from SecurityRoleEntityDBImpl sr where sr.roleName in (:roleNames) ", "roleNames", roleNames);
		List<SecurityRoleEntityDBImpl> roles = find;
		return roles;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<SecurityRoleEntityDBImpl> findRoleById(Long... ids) {
    List find = getHibernateTemplate().findByNamedParam("select sr from SecurityRoleEntityDBImpl sr where sr.security_role_id in (:ids) ", "ids", ids);
		List<SecurityRoleEntityDBImpl> roles = find;
		return roles;
	}

  @Override
  public List<String> listRoleNames() {
    List roleNames = getHibernateTemplate().find("select distinct sr.roleName from SecurityRoleEntityDBImpl sr");
    return roleNames;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<SecurityRoleEntityDBImpl> listRoles() {
		List find = getHibernateTemplate().loadAll(SecurityRoleEntityDBImpl.class);
		List<SecurityRoleEntityDBImpl> roles = find;
		return roles;
	}


}
