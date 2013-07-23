/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.handlers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class AccessDeniedHandlerImpl extends org.springframework.security.web.access.AccessDeniedHandlerImpl {

  public AccessDeniedHandlerImpl() {
    super();
  }

  public AccessDeniedHandlerImpl(String accessDeniedUrl) {
    super();
    setErrorPage(accessDeniedUrl);
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException,
    ServletException {
    request.getSession().setAttribute("message", accessDeniedException.getLocalizedMessage());
    super.handle(request,response,accessDeniedException);
  }
}
