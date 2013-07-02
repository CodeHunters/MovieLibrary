package gr.codehunters.MovieLibrary.exceptions;

import java.util.HashMap;
import java.util.Map;

public enum ExceptionMessageType {
  PASSWORD_MISMATCH(1, "PASSWORD_MISMATCH", "Password Mismatch"),
  PASSWORD_INVALID(2, "PASSWORD_INVALID", "Invalid Password");

  int id;
  String localeId;
  String defaultMessage;

  private static final Map<String, ExceptionMessageType> enumerationMap;

  static {
    enumerationMap = new HashMap<String, ExceptionMessageType>();
    for (ExceptionMessageType v : values()) {
      enumerationMap.put(v.toString(), v);
      enumerationMap.put(String.valueOf(v.id), v);
      enumerationMap.put(String.valueOf(v.localeId), v);
      enumerationMap.put(String.valueOf(v.defaultMessage), v);
    }
  }

  private ExceptionMessageType(int id, String localteID, String defaultMessage) {
    this.id = id;
    this.defaultMessage = defaultMessage;
    this.localeId = localteID;
  }

  public String getDefaultMessage() {
    return defaultMessage;
  }

  public void setDefaultMessage(String defaultMessage) {
    this.defaultMessage = defaultMessage;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLocaleId() {
    return localeId;
  }

  public void setLocaleId(String localeId) {
    this.localeId = localeId;
  }

  public static ExceptionMessageType getExceptionMessageType(Object identifier) {
    return enumerationMap.get(identifier.toString());
  }

  public boolean isOfStatus(ExceptionMessageType... fileTypes) {
    for (ExceptionMessageType fileType : fileTypes) {
      if (fileType.getId() == this.getId()) {
        return true;
      }
    }
    return false;
  }
}
