package gr.codehunters.MovieLibrary.model;

public interface SecurityRoleEntity<ID,NAME,SOURCE,DESTINATION> extends Identifiable<ID>,Nameable<NAME>,Convertable<SOURCE,DESTINATION>{


 	public ID getPersonId();
 	public void setPersonId(ID personId);
 	public NAME getRoleName();
 	public void setRoleName(NAME roleName);
 	public ID getSecurity_role_id();
 	public void setSecurity_role_id(ID security_role_id);
}