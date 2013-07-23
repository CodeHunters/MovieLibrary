/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.security;

import org.springframework.security.core.Authentication;

public interface Permission {
  boolean isAllowed(Authentication authentication, Object targetObject);
}