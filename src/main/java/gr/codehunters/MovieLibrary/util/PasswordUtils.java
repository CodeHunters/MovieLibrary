/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.util;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class PasswordUtils {
  
  public static String encrypt(String password) {
    PasswordEncoder encoder = new Md5PasswordEncoder();
    String hashedPass = encoder.encodePassword(password, null);
    return hashedPass;
  }
  
}
