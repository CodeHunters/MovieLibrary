package gr.codehunters.MovieLibrary.util;

import org.codehaus.jackson.annotate.JsonValue;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonTableResponse implements Serializable {
  private static final String KEY_RESULT = "Result";
  private static final String RESULT_OK = "OK";
  private static final String RESULT_ERROR = "ERROR";
  private static final String RESULT_ERROR_SILENT = "ERROR_SILENT";
  private static final String KEY_RECORD = "Record";
  private static final String KEY_RECORDS = "Records";
  private static final String KEY_RECORDSCOUNT = "TotalRecordCount";
  private static final String KEY_MESSAGE = "Message";
  private static final String NEW_LINE = "/n";
  private Map<String, Object> jsonResponseMap;

  public JsonTableResponse() {
    jsonResponseMap = new HashMap<String, Object>();
  }

  public JsonTableResponse error(String errorMessage) {
    jsonResponseMap.put(KEY_RESULT, RESULT_ERROR);
    jsonResponseMap.put(KEY_MESSAGE, errorMessage);
    return this;
  }

  public JsonTableResponse errorNoPopup(String errorMessage) {
    jsonResponseMap.put(KEY_RESULT, RESULT_ERROR_SILENT);
    jsonResponseMap.put(KEY_MESSAGE, errorMessage);
    return this;
  }

  public JsonTableResponse error(List<ObjectError> errorList) {
    jsonResponseMap.put(KEY_RESULT, RESULT_ERROR);
    StringBuilder sb = new StringBuilder();
    for (ObjectError err : errorList) {
      sb.append(err.getDefaultMessage()).append(NEW_LINE);
    }
    jsonResponseMap.put(KEY_MESSAGE, sb.toString());
    return this;
  }

  public JsonTableResponse ok(Object result) {
    jsonResponseMap.put(KEY_RESULT, RESULT_OK);
    jsonResponseMap.put(KEY_RECORD, result);
    return this;
  }

  public JsonTableResponse ok(Set<? extends Object> resultList, long recordsCount) {
    jsonResponseMap.put(KEY_RESULT, RESULT_OK);
    jsonResponseMap.put(KEY_RECORDS, resultList);
    jsonResponseMap.put(KEY_RECORDSCOUNT, recordsCount);
    return this;
  }

  public JsonTableResponse ok() {
    jsonResponseMap.put(KEY_RESULT, RESULT_OK);
    return this;
  }

  @JsonValue
  public Map<String, Object> getJsonResponseMap() {
    return jsonResponseMap;
  }
}
