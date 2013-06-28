/*
 *  Copyright 2013 ADVA AG Optical Networking. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.dao.core;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Find {

  @Autowired
  private SessionFactory sessionFactory;

  private String fullyQualifiedClassName = null;

  public Set execute() {
      Set rv = new HashSet();
      Session session = sessionFactory.openSession();
      try {
          rv.addAll(session.createQuery("from " + getFullyQualifiedClassName()).list());
      } finally {
          if (session != null) {
              session.close();
          }
      }
      return rv;
  }

  public Set execute(Integer firstResult, Integer maxResults, List<Order> orders, List<Criterion> criterions) {
      Set rv = new HashSet();
      Session session = sessionFactory.openSession();
      try {
          Criteria criteria=session.createCriteria(getFullyQualifiedClassName());
          if (maxResults!=null) criteria.setMaxResults(maxResults);
          if (firstResult!=null) criteria.setFirstResult(firstResult+1);
          if (orders!=null){
            for (Order order : orders) {
              criteria.addOrder(order);
            }
          }
         if (criterions!=null){
           for (Criterion criterion : criterions) {
             criteria.add(criterion);
           }
         }
          rv.addAll(criteria.list());
      } finally {
          if (session != null) {

              session.close();
          }
      }
      return rv;
  }

  public String getFullyQualifiedClassName() {
      return this.fullyQualifiedClassName;
  }

  public void setFullyQualifiedClassName(String _fullyQualifiedClassName) {
      this.fullyQualifiedClassName = _fullyQualifiedClassName;
  }

  public Long count() {
    Long cnt = null;
    Session session = sessionFactory.openSession();
    try {
        cnt = (Long)session.createQuery("select count(*)from " + getFullyQualifiedClassName()).uniqueResult();
    } finally {
        if (session != null) {
            session.close();
        }
    }
    return cnt;
  }
}
