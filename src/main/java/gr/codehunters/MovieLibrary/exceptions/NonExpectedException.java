/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.exceptions;

public class NonExpectedException extends AbstractLocalizedException {

  public NonExpectedException() {
    super(ExceptionMessageType.NON_EXPECTED_EXCEPTION);
  }

  @Override
  protected String translateMessage(String message) {
    return getExceptionMessageType().getDefaultMessage();
  }
}