/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.init;

import gr.codehunters.MovieLibrary.factories.SecurityRoleFactory;
import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntity;
import gr.codehunters.MovieLibrary.model.users.UserEntity;
import gr.codehunters.MovieLibrary.dao.SecurityRolesDAO;
import gr.codehunters.MovieLibrary.dao.UserDAO;
import org.springframework.context.ApplicationListener;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationContextListener implements ApplicationListener<ApplicationEvent> {
    @Autowired
    SecurityRolesDAO securityRolesDAO;
    @Autowired
    UserDAO userDao;

    private static String[] roleNames={"ADMINISTRATOR", "SUBSCRIBER", "USER"};

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent)
		{
			initializeSecurityRoles();
			initializeUsers();
		}

	}

	private void initializeUsers() {
		Set<UserEntity> users=userDao.listUser();
		if (users.size()==0){
			UserEntity admin=new UserEntity("admin","admin","admin","admin","admin");
			admin.getUserSecurityRoleEntity().addAll(securityRolesDAO.listRoles());
			userDao.save(admin);
		}
	}

	private void initializeSecurityRoles(){
		List<SecurityRoleEntity> rolesFromDB=securityRolesDAO.listRoles();
		if (rolesFromDB.size()==0){
			SecurityRoleFactory roleFactory=new SecurityRoleFactory();
			List<SecurityRoleEntity> roles=roleFactory.generateSecurityRoles(roleNames);
			for (SecurityRoleEntity role:roles){
				securityRolesDAO.save(role);
			}
		}
	}
}

