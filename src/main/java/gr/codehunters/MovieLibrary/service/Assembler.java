package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntity;
import gr.codehunters.MovieLibrary.model.users.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service("assembler")
public class Assembler {
	User buildUserFromUserEntity(UserEntity userEntity) {
		String username = userEntity.getUserName();
		String password = userEntity.getPassword();
		boolean enabled = userEntity.isActive();
		boolean accountNonExpired = userEntity.isActive();
		boolean credentialsNonExpired = userEntity.isActive();
		boolean accountNonLocked = userEntity.isActive();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		Set<SecurityRoleEntity> roles=userEntity.getUserSecurityRoleEntity();
		for (SecurityRoleEntity role: roles){
			authorities.add(new GrantedAuthorityImpl(role.getRoleName()));	
		}
    return new User(username, password, enabled, accountNonExpired,credentialsNonExpired, accountNonLocked, authorities);
	}
}