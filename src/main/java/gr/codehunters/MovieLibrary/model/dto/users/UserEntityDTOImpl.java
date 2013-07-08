package gr.codehunters.MovieLibrary.model.dto.users;

import gr.codehunters.MovieLibrary.exceptions.PasswordException;
import gr.codehunters.MovieLibrary.model.UserEntity;
import gr.codehunters.MovieLibrary.model.db.users.AddressEntityDBImpl;
import gr.codehunters.MovieLibrary.model.db.users.SecurityRoleEntityDBImpl;
import gr.codehunters.MovieLibrary.model.db.users.UserEntityDBImpl;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class UserEntityDTOImpl implements UserEntity<Long, String, UserEntityDBImpl, UserEntityDTOImpl, AddressEntityDTOImpl, SecurityRoleEntityDTOImpl> {
  private Long user_id;
  private String first_name;
  private String last_name;
  private String userName;
  private String gender;
  private String aboutYou;
  private String password;
  private boolean isActive;
  private boolean alertsEnabled;
  private Set<AddressEntityDTOImpl> userAddress = new HashSet<AddressEntityDTOImpl>(0);
  private Set<SecurityRoleEntityDTOImpl> userSecurityRoleEntity = new HashSet<SecurityRoleEntityDTOImpl>(0);

  public UserEntityDTOImpl(UserEntityDBImpl userEntityDB) {
    this.user_id = userEntityDB.getUser_id();
    this.first_name = userEntityDB.getFirst_name();
    this.last_name = userEntityDB.getLast_name();
    this.userName = userEntityDB.getUserName();
    this.gender = userEntityDB.getGender();
    this.aboutYou = userEntityDB.getAboutYou();
    this.password = userEntityDB.getPassword();
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
    this.setPassword(encrypt(password));
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

  private String encrypt(String password) {
    PasswordEncoder encoder = new Md5PasswordEncoder();
    String hashedPass = encoder.encodePassword(password, null);
    return hashedPass;
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
    this.password = userEntityDB.getPassword();
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
