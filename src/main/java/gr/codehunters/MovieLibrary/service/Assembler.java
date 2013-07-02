package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntityDBImpl;
import gr.codehunters.MovieLibrary.model.users.UserEntityDBImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service("assembler")
public class Assembler {

	User buildUserFromUserEntity(UserEntityDBImpl userEntity) {
		String username = userEntity.getUserName();
		String password = userEntity.getPassword();
		boolean enabled = userEntity.isActive();
		boolean accountNonExpired = userEntity.isActive();
		boolean credentialsNonExpired = userEntity.isActive();
		boolean accountNonLocked = userEntity.isActive();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		Set<SecurityRoleEntityDBImpl> roles=userEntity.getUserSecurityRoleEntity();
		for (SecurityRoleEntityDBImpl role: roles){
			authorities.add(new GrantedAuthorityImpl(role.getRoleName()));	
		}
    return new User(username, password, enabled, accountNonExpired,credentialsNonExpired, accountNonLocked, authorities);
	}
}