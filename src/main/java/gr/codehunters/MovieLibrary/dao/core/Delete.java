package gr.codehunters.MovieLibrary.dao.core;

import org.hibernate.Session;

public class Delete extends SimpleAction {
  public void doAction(final Session session, final Object o) {
      session.delete(o);
  }
}
