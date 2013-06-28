package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.dao.UserDAO;
import gr.codehunters.MovieLibrary.model.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDAO userDao;
	@Autowired
	private Assembler assembler;

	public UserDetails loadUserByUsername(String username)      throws UsernameNotFoundException, DataAccessException {
		UserEntity userEntity = userDao.findByUserName(username);
		if (userEntity == null){
			throw new UsernameNotFoundException("user not found");    
		}
		return assembler.buildUserFromUserEntity(userEntity);  
    }
}