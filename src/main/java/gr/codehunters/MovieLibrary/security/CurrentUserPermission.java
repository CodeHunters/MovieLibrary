package gr.codehunters.MovieLibrary.security;

import gr.codehunters.MovieLibrary.exceptions.UserNotFoundException;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import gr.codehunters.MovieLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;

@Component(value = "currentUserPermission")
public class CurrentUserPermission implements Permission {
  @Autowired
  UserService userService;

  @Override
  public boolean isAllowed(Authentication authentication, Object target) {
    try {
    if (target != null && SecurityContextHolderAwareRequestWrapper.class.isAssignableFrom(target.getClass())) {
      SecurityContextHolderAwareRequestWrapper request = (SecurityContextHolderAwareRequestWrapper) target;
      return (isAuthenticated(authentication) && isCurrentUser(request, authentication));
    } else {
      return false;
    }
    }catch (Exception ex){
      return false;
    }
  }

  private boolean isCurrentUser(SecurityContextHolderAwareRequestWrapper request, Authentication authentication) throws UserNotFoundException {
    UserEntityDTOImpl targetUser = (UserEntityDTOImpl) userService.getUserById(new Long(request.getParameter("id")));
    return authentication.getPrincipal() != null && User.class.isAssignableFrom(authentication.getPrincipal().getClass()) &&
      (targetUser != null && ((User) (authentication.getPrincipal())).getUsername().compareTo(targetUser.getUserName()) == 0);
  }

  private boolean isAuthenticated(Authentication authentication) {
    return authentication != null && authentication.isAuthenticated();
  }
}
