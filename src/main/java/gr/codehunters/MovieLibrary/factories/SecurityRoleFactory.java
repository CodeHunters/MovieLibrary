package gr.codehunters.MovieLibrary.factories;

import gr.codehunters.MovieLibrary.model.db.users.SecurityRoleEntityDBImpl;

import java.util.ArrayList;
import java.util.List;

public class SecurityRoleFactory {
		
    public List<SecurityRoleEntityDBImpl> generateSecurityRoles(String[] roleNames){
    	List<SecurityRoleEntityDBImpl> roles=new ArrayList<SecurityRoleEntityDBImpl>();
    	for (String roleName:roleNames){
    		roles.add(new SecurityRoleEntityDBImpl(roleName));
    	}
    	return roles;
    }
}
