package gr.codehunters.MovieLibrary.model.db.users;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import gr.codehunters.MovieLibrary.exceptions.PasswordException;
import gr.codehunters.MovieLibrary.model.UserEntity;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import gr.codehunters.MovieLibrary.util.PasswordUtils;

@Entity
@Table(name = "user")
public class UserEntityDBImpl implements UserEntity<Long,String,UserEntityDTOImpl,UserEntityDBImpl,AddressEntityDBImpl,SecurityRoleEntityDBImpl> {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long user_id;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "family_name")
	private String last_name;
	@Column(name = "userName", unique = true)
	private String userName;
	@Column(name = "gender")
	private String gender;	
	@Column(name = "aboutYou")
	private String aboutYou;		
	@Column(name = "password")
	private String password;
	@Column(name = "active", nullable=false)
	private boolean isActive;
	@Column(name = "alertsEnabled", nullable=false)
	private boolean alertsEnabled;
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "user_address", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "address_id") })
	private Set<AddressEntityDBImpl> userAddress = new HashSet<AddressEntityDBImpl>(0);
	@ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH}, fetch=FetchType.EAGER)
	@JoinTable(name = "user_security_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "security_role_id") })
	private Set<SecurityRoleEntityDBImpl> userSecurityRoleEntity = new HashSet<SecurityRoleEntityDBImpl>(0);

  public UserEntityDBImpl(UserEntityDTOImpl userEntity) {
    setAboutYou(userEntity.getAboutYou());
    setActive(userEntity.isActive());
    setAlertsEnabled(userEntity.isAlertsEnabled());
    setFirst_name(userEntity.getFirst_name());
    setGender(userEntity.getGender());
    setLast_name(userEntity.getLast_name());
    setPassword(PasswordUtils.encrypt(userEntity.getPassword()));
    setName(userEntity.getName());
  }

  public boolean isAlertsEnabled() {
 		return alertsEnabled;
 	}



 	public void setAlertsEnabled(boolean alertsEnabled) {
 		this.alertsEnabled = alertsEnabled;
 	}

	
	@SuppressWarnings("UnusedDeclaration")
  public UserEntityDBImpl() {
		super();
	}



	public UserEntityDBImpl(String first_name, String last_name,
                          String userName, String password,
                          String confirmPassword) throws PasswordException {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.userName = userName;
		this.setPassword(PasswordUtils.encrypt(password));
		this.isActive = true;
    if (password.compareTo(confirmPassword)!=0){
      throw new PasswordException();
    }
	}

	

	public Set<AddressEntityDBImpl> getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(Set<AddressEntityDBImpl> userAddress) {
		this.userAddress = userAddress;
	}

	/*****************************************************************************/
	public Set<SecurityRoleEntityDBImpl> getUserSecurityRoleEntity() {
		return this.userSecurityRoleEntity;
	}

	public void setUserSecurityRoleEntity(
			Set<SecurityRoleEntityDBImpl> userSecurityRoleEntity) {
		this.userSecurityRoleEntity = userSecurityRoleEntity;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return user_id;
	}

	public void setId(Long id) {
		this.user_id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

  @Override
  public String getName() {
    return userName;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setName(String name) {
    userName=name;
  }

  public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName=userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAboutYou() {
		return aboutYou;
	}

	public void setAboutYou(String aboutYou) {
		this.aboutYou = aboutYou;
	}

  @Override
  public UserEntityDTOImpl createImp() {
    return new UserEntityDTOImpl(this);
  }

  @Override
  public UserEntityDBImpl resynch(UserEntityDTOImpl userEntityDTO) {
   	this.user_id=userEntityDTO.getUser_id();
   	this.first_name=userEntityDTO.getFirst_name();
   	this.last_name=userEntityDTO.getLast_name();
   	this.userName=userEntityDTO.getUserName();
   	this.gender=userEntityDTO.getGender();
   	this.aboutYou=userEntityDTO.getAboutYou();
   	this.isActive=userEntityDTO.isActive();
   	this.alertsEnabled=userEntityDTO.isAlertsEnabled();
    if (userEntityDTO.getPassword()!=null){
      this.password=PasswordUtils.encrypt(userEntityDTO.getPassword());
    }
    return this;
  }
}
