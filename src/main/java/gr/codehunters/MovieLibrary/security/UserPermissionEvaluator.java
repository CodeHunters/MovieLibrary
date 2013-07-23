package gr.codehunters.MovieLibrary.security;

import gr.codehunters.MovieLibrary.exceptions.PermissionNotDefinedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Map;

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
  private Map<String,Permission> permissions;

  protected UserPermissionEvaluator() {
  }


  @Override
  @Transactional
  public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
    boolean hasPermission = false;
    if (canHandle(authentication, targetDomainObject, permission)) {
      hasPermission = checkPermission(authentication, targetDomainObject, (String) permission);
    }
    return hasPermission;
  }

  private boolean canHandle(Authentication authentication, Object targetDomainObject, Object permission) {
    return targetDomainObject != null && authentication != null && permission instanceof String && permissions.containsKey(permission);
  }

  private boolean checkPermission(Authentication authentication, Object targetDomainObject, String permissionKey) {
    return permissions.get(permissionKey).isAllowed(authentication, targetDomainObject);
  }

  @Override
  public boolean hasPermission(Authentication authentication, Serializable target, String targetType, Object permission) {
    throw new PermissionNotDefinedException("Target and Class permissions are not supported by " + this.getClass().toString());
  }

  public Map<String, Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Map<String, Permission> permissions) {
    this.permissions = permissions;
  }
}
