package gr.codehunters.MovieLibrary.exceptions;

public abstract class AbstractLocalizedException extends Exception {
  ExceptionMessageType exceptionMessageType;
  public String specialMsg;
  public AbstractLocalizedException(ExceptionMessageType exceptionMessageType) {
    super();
    this.exceptionMessageType=exceptionMessageType;
    specialMsg=getLocalizedMessage();
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

  public String getSpecialMsg() {
    return specialMsg;
  }
}
