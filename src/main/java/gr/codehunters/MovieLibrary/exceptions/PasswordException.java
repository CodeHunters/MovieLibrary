package gr.codehunters.MovieLibrary.exceptions;

public class PasswordException extends AbstractLocalizedException {

  public PasswordException() {
    super(ExceptionMessageType.PASSWORD_MISMATCH);
  }

  @Override
  protected String translateMessage(String message) {
    return getExceptionMessageType().getDefaultMessage();
  }
}
