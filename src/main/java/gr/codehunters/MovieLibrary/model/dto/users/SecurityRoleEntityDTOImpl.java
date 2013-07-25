package gr.codehunters.MovieLibrary.model.dto.users;

import gr.codehunters.MovieLibrary.model.SecurityRoleEntity;
import gr.codehunters.MovieLibrary.model.db.users.SecurityRoleEntityDBImpl;

public class SecurityRoleEntityDTOImpl implements SecurityRoleEntity<Long,String,SecurityRoleEntityDBImpl, SecurityRoleEntityDTOImpl> {
	private Long security_role_id;
	String roleName;

	public SecurityRoleEntityDTOImpl(String roleName) {
		this.roleName=roleName;
	}
	
	public SecurityRoleEntityDTOImpl() {
	}

  public SecurityRoleEntityDTOImpl(SecurityRoleEntityDBImpl securityRoleEntityDB) {
    this.setName(securityRoleEntityDB.getName());
    this.setId(securityRoleEntityDB.getId());
  }

  public Long getId() {
		return security_role_id;
	}

  @Override
  public void setId(Long id) {
    security_role_id=id;
  }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getSecurity_role_id() {
		return security_role_id;
	}

	public void setSecurity_role_id(Long security_role_id) {
		this.security_role_id = security_role_id;
	}

  @Override
  public SecurityRoleEntityDBImpl createImp() {
    return null;
  }

  @Override
  public SecurityRoleEntityDTOImpl resynch(SecurityRoleEntityDBImpl securityRoleEntityDB) {
     this.security_role_id=securityRoleEntityDB.getId();
    this.roleName=securityRoleEntityDB.getRoleName();
    return this;
  }

  @Override
  public String getName() {
    return getRoleName();
  }

  @Override
  public void setName(String name) {
    setRoleName(name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SecurityRoleEntityDTOImpl)) return false;

    SecurityRoleEntityDTOImpl that = (SecurityRoleEntityDTOImpl) o;

    return roleName.equals(that.roleName) && security_role_id.equals(that.security_role_id);

  }

  @Override
  public int hashCode() {
    int result = security_role_id.hashCode();
    result = 31 * result + roleName.hashCode();
    return result;
  }
}
