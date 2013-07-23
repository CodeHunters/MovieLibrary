package gr.codehunters.MovieLibrary.model.dto.users;

import gr.codehunters.MovieLibrary.exceptions.PasswordException;
import gr.codehunters.MovieLibrary.model.UserEntity;
import gr.codehunters.MovieLibrary.model.db.users.AddressEntityDBImpl;
import gr.codehunters.MovieLibrary.model.db.users.SecurityRoleEntityDBImpl;
import gr.codehunters.MovieLibrary.model.db.users.UserEntityDBImpl;
import gr.codehunters.MovieLibrary.util.PasswordUtils;
import gr.codehunters.MovieLibrary.validator.ContentMatch;
import gr.codehunters.MovieLibrary.validator.UniqueKey;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@UniqueKey(columnNames={"userName"},objectIdentifierName ="user_id", clazzName = UserEntityDBImpl.class,message = "This user already exists")
@ContentMatch(fields = {"password","password_verify"},message = "Password fields do not match")
public class UserEntityDTOImpl implements UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl>,Serializable {
  private Long user_id;
  @NotNull(message = "User first name cannot be empty")
  @Size(min = 1,message = "User first name cannot be less than 1 character")
  private String first_name;
  @NotNull(message = "User last name cannot be empty")
  @Size(min = 1,message = "User last name cannot be less than 1 character")
  private String last_name;
  @NotNull(message = "User name cannot be empty")
  @Size(min = 3,message = "User name cannot be less than 3 characters")
  private String userName;
  private String gender;
  private String aboutYou;
  @Pattern(regexp = "(((?=.*\\d)((?=.*[a-z])|(?=.*[A-Z]))(?=.*[@#$%!]).{6,20})|PREDEFINED)",message = "User password is too weak")
  private String password;
  private String password_verify;
  private boolean isActive;
  private boolean alertsEnabled;
  private Set<AddressEntityDTOImpl> userAddress = new HashSet<AddressEntityDTOImpl>(0);
  @NotNull(message = "User must have at least one role assigned")
  @Size(min = 1,message = "User must have at least one role assigned")
  private Set<SecurityRoleEntityDTOImpl> userSecurityRoleEntity = new HashSet<SecurityRoleEntityDTOImpl>(0);

  public UserEntityDTOImpl(UserEntityDBImpl userEntityDB) {
    this.user_id = userEntityDB.getUser_id();
    this.first_name = userEntityDB.getFirst_name();
    this.last_name = userEntityDB.getLast_name();
    this.userName = userEntityDB.getUserName();
    this.gender = userEntityDB.getGender();
    this.aboutYou = userEntityDB.getAboutYou();
    this.isActive = userEntityDB.isActive();
    this.alertsEnabled = userEntityDB.isAlertsEnabled();
    for (AddressEntityDBImpl userAddressEntityDB : userEntityDB.getUserAddress()) {
      userAddress.add(userAddressEntityDB.createImp());
    }
    for (SecurityRoleEntityDBImpl securityRoleEntityDB : userEntityDB.getUserSecurityRoleEntity()) {
      userSecurityRoleEntity.add(securityRoleEntityDB.createImp());
    }
  }

  public boolean isAlertsEnabled() {
    return alertsEnabled;
  }


  public void setAlertsEnabled(boolean alertsEnabled) {
    this.alertsEnabled = alertsEnabled;
  }


  public UserEntityDTOImpl() {
    super();
  }


  public UserEntityDTOImpl(String first_name, String last_name,
                           String userName, String password,
                           String confirmPassword) throws PasswordException {
    super();
    this.first_name = first_name;
    this.last_name = last_name;
    this.userName = userName;
    this.setPassword(PasswordUtils.encrypt(password));
    this.isActive = true;
    if (password.compareTo(confirmPassword) != 0) {
      throw new PasswordException();
    }
  }


  public Set<AddressEntityDTOImpl> getUserAddress() {
    return this.userAddress;
  }

  public void setUserAddress(Set<AddressEntityDTOImpl> userAddress) {
    this.userAddress = userAddress;
  }

  public Set<SecurityRoleEntityDTOImpl> getUserSecurityRoleEntity() {
    return this.userSecurityRoleEntity;
  }

  public void setUserSecurityRoleEntity(Set<SecurityRoleEntityDTOImpl> userSecurityRoleEntity) {
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
    return userName;
  }

  @Override
  public void setName(String name) {
    userName = name;
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
    this.userName = userName;
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

  public String getPassword_verify() {
    return password_verify;
  }

  public void setPassword_verify(String password_verify) {
    this.password_verify = password_verify;
  }

  @Override
  public UserEntityDBImpl createImp() {
    return new UserEntityDBImpl(this);
  }

  @Override
  public UserEntityDTOImpl resynch(UserEntityDBImpl userEntityDB) {
    this.user_id = userEntityDB.getUser_id();
    this.first_name = userEntityDB.getFirst_name();
    this.last_name = userEntityDB.getLast_name();
    this.userName = userEntityDB.getUserName();
    this.gender = userEntityDB.getGender();
    this.aboutYou = userEntityDB.getAboutYou();
    this.isActive = userEntityDB.isActive();
    this.alertsEnabled = userEntityDB.isAlertsEnabled();
    userAddress.clear();
    for (AddressEntityDBImpl userAddressEntry : userEntityDB.getUserAddress()) {
      userAddress.add(userAddressEntry.createImp());
    }
    userAddress.clear();
    for (SecurityRoleEntityDBImpl securityRoleEntityDBEntry : userEntityDB.getUserSecurityRoleEntity()) {
      userSecurityRoleEntity.add(securityRoleEntityDBEntry.createImp());
    }
    return this;
  }
}
