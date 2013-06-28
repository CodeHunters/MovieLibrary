/*
 *  Copyright 2013 ADVA AG Optical Networking. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.util;

import com.google.gson.Gson;

import java.util.List;

public class GsonUtils {
  public String objectToJson(Object item,int items){
    return "{ \"Result\":\"OK\", \"Records\":"+(new Gson()).toJson(item)+",\"TotalRecordCount\":"+items+"}";
  }
}
