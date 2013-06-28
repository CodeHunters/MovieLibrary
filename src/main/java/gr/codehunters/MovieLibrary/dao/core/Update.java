package gr.codehunters.MovieLibrary.dao.core;

import org.hibernate.Session;

public class Update extends SimpleAction {
  public void doAction(final Session session, final Object o) {
      session.update(o);
  }
}
