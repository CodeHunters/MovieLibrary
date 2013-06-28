/*
 *  Copyright 2013 ADVA AG Optical Networking. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.dao.core;

import org.hibernate.Session;

public class Update extends SimpleAction {
  public void doAction(final Session session, final Object o) {
      session.update(o);
  }
}
