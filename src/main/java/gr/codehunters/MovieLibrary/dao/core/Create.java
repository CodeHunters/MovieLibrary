package gr.codehunters.MovieLibrary.dao.core;

import org.hibernate.Session;

public class Create extends SimpleAction {
  public void doAction(final Session session, final Object o) {
      session.save(o);
  }

}