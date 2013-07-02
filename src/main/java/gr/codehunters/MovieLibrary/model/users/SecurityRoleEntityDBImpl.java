package gr.codehunters.MovieLibrary.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security_role_entity")
public class SecurityRoleEntityDBImpl {
	@Id
	@Column(name = "security_role_id")
  @GeneratedValue
	private long security_role_id;
	@Column(name = "user_id")
	private long personId;
	
	@Column(name="roleName")
	String roleName;

	public SecurityRoleEntityDBImpl(String roleName) {
		this.roleName=roleName;
	}
	
	public SecurityRoleEntityDBImpl() {
	}

	public long getId() {
		return security_role_id;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getSecurity_role_id() {
		return security_role_id;
	}

	public void setSecurity_role_id(long security_role_id) {
		this.security_role_id = security_role_id;
	}

}
