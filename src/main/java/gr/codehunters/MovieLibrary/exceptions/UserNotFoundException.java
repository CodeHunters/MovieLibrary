/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.exceptions;

public class UserNotFoundException extends AbstractLocalizedException {

  public UserNotFoundException() {
    super(ExceptionMessageType.USER_NOT_FOUND);
  }

  @Override
  protected String translateMessage(String message) {
    return getExceptionMessageType().getDefaultMessage();
  }
}
