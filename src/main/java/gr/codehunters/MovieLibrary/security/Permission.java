package gr.codehunters.MovieLibrary.security;

import org.springframework.security.core.Authentication;

public interface Permission {
  boolean isAllowed(Authentication authentication, Object targetObject);
}