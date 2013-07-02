package gr.codehunters.MovieLibrary.init;

import gr.codehunters.MovieLibrary.exceptions.PasswordException;
import gr.codehunters.MovieLibrary.factories.SecurityRoleFactory;
import gr.codehunters.MovieLibrary.model.users.SecurityRoleEntityDBImpl;
import gr.codehunters.MovieLibrary.model.users.UserEntityDBImpl;
import gr.codehunters.MovieLibrary.dao.SecurityRolesDAO;
import gr.codehunters.MovieLibrary.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationContextListener implements ApplicationListener<ApplicationEvent> {
  private static final Logger log = LoggerFactory.getLogger(ApplicationContextListener.class.getName());
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
		Set<UserEntityDBImpl> users=userDao.listUser();
		if (users.size()==0){
      UserEntityDBImpl admin;
      try {
        admin = new UserEntityDBImpl("admin","admin","admin","admin","admin");
        admin.getUserSecurityRoleEntity().addAll(securityRolesDAO.listRoles());
  			userDao.save(admin);
      } catch (PasswordException e) {
         log.error(e.getMessage());
      }
		}
	}

	private void initializeSecurityRoles(){
		List<SecurityRoleEntityDBImpl> rolesFromDB=securityRolesDAO.listRoles();
		if (rolesFromDB.size()==0){
			SecurityRoleFactory roleFactory=new SecurityRoleFactory();
			List<SecurityRoleEntityDBImpl> roles=roleFactory.generateSecurityRoles(roleNames);
			for (SecurityRoleEntityDBImpl role:roles){
				securityRolesDAO.save(role);
			}
		}
	}
}

