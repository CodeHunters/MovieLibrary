package gr.codehunters.MovieLibrary.exceptions;

public abstract class AbstractLocalizedException extends Exception {
  ExceptionMessageType exceptionMessageType;
  public AbstractLocalizedException(ExceptionMessageType exceptionMessageType) {
    super();
    this.exceptionMessageType=exceptionMessageType;
  }

  public String getMessage() {
    return translateMessage(super.getMessage());
  }

  public String getLocalizedMessage() {
    return translateMessage(super.getMessage());
  }

  protected abstract String translateMessage(final String message);

  public ExceptionMessageType getExceptionMessageType() {
    return exceptionMessageType;
  }
}
