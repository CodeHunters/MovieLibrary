package gr.codehunters.MovieLibrary.model;

import java.util.Set;

public interface UserEntity<ID,NAME,SOURCE,DESTINATION,ADDRESSENTITY,SECURITYENTITY> extends Identifiable<ID>,Nameable<NAME>,Convertable<SOURCE,DESTINATION>{
  public boolean isAlertsEnabled();
  public void setAlertsEnabled(boolean alertsEnabled);
 	public Set<ADDRESSENTITY> getUserAddress();
 	public void setUserAddress(Set<ADDRESSENTITY> userAddress);
 	public Set<SECURITYENTITY> getUserSecurityRoleEntity();
 	public void setUserSecurityRoleEntity(Set<SECURITYENTITY> userSecurityRoleEntity);
 	public boolean isActive();
 	public void setActive(boolean isActive);
 	public ID getId();
 	public void setId(ID id);
 	public String getFirst_name();
  public NAME getName();
 	public void setFirst_name(String first_name);
 	public String getLast_name();
 	public void setLast_name(String last_name);
 	public String getUserName();
 	public void setUserName(String userName);
 	public String getPassword();
 	public void setPassword(String password);
 	public ID getUser_id();
 	public void setUser_id(ID user_id);
 	public String getGender();
 	public void setGender(String gender);
 	public String getAboutYou();
 	public void setAboutYou(String aboutYou);
}