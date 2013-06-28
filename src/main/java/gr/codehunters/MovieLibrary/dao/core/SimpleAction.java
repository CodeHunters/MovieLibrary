package gr.codehunters.MovieLibrary.dao.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class SimpleAction {

  @Autowired
  private SessionFactory sessionFactory = null;

  abstract void doAction(final Session session, final Object o);

  public void execute(final Object o) {
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      doAction(session, o);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  public SessionFactory getSessionFactory() {
    return this.sessionFactory;
  }

  public void setSessionFactory(SessionFactory _sessionFactory) {
    this.sessionFactory = _sessionFactory;
  }

}
