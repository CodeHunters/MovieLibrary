package gr.codehunters.MovieLibrary.model.db.users;

import gr.codehunters.MovieLibrary.model.SecurityRoleEntity;
import gr.codehunters.MovieLibrary.model.dto.users.SecurityRoleEntityDTOImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security_role_entity")
public class SecurityRoleEntityDBImpl implements SecurityRoleEntity<Long,String,SecurityRoleEntityDTOImpl,SecurityRoleEntityDBImpl> {
	@Id
	@Column(name = "security_role_id")
  @GeneratedValue
	private Long security_role_id;

	@Column(name="roleName")
	String roleName;

	public SecurityRoleEntityDBImpl(String roleName) {
		this.roleName=roleName;
	}
	
	public SecurityRoleEntityDBImpl() {
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

  @Override
  public void setSecurity_role_id(Long security_role_id) {
   this.security_role_id=security_role_id;
  }

  @Override
  public SecurityRoleEntityDTOImpl createImp() {
    return new SecurityRoleEntityDTOImpl(this);
  }

  @Override
  public SecurityRoleEntityDBImpl resynch(SecurityRoleEntityDTOImpl securityRoleEntityDTO) {
    this.setName(securityRoleEntityDTO.getName());
    return this;
  }

  @Override
  public String getName() {
    return roleName;
  }

  @Override
  public void setName(String name) {
   this.roleName=name;
  }
}
