package gr.codehunters.MovieLibrary.factories;

import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntity;

import java.util.ArrayList;
import java.util.List;

public class SecurityRoleFactory {
		
    public List<SecurityRoleEntity> generateSecurityRoles(String[] roleNames){
    	List<SecurityRoleEntity> roles=new ArrayList<SecurityRoleEntity>();
    	for (String roleName:roleNames){
    		roles.add(new SecurityRoleEntity(roleName));
    	}
    	return roles;
    }
}
