package gr.codehunters.MovieLibrary.model.users;

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
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

@Entity
@Table(name = "user")
public class UserEntityDBImpl {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "family_name")
	private String last_name;
	@Column(name = "userName")
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
	/*******************************************************************************/
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "user_security_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "security_role_id") })
	private Set<SecurityRoleEntityDBImpl> userSecurityRoleEntity = new HashSet<SecurityRoleEntityDBImpl>(0);

  public boolean isAlertsEnabled() {
 		return alertsEnabled;
 	}



 	public void setAlertsEnabled(boolean alertsEnabled) {
 		this.alertsEnabled = alertsEnabled;
 	}

	
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
		this.setPassword(password);
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

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public String getFirst_name() {
		return first_name;
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
		this.password = encrypt(password);
	}

  private String encrypt(String password) {
    PasswordEncoder encoder = new Md5PasswordEncoder();
    String hashedPass = encoder.encodePassword(password, null);
    return hashedPass;
  }

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
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

}
