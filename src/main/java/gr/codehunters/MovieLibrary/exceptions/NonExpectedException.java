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