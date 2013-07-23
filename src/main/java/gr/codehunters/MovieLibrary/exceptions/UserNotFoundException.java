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
