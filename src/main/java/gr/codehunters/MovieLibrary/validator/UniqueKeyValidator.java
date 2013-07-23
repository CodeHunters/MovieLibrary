package gr.codehunters.MovieLibrary.validator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

@Component
public class UniqueKeyValidator implements ConstraintValidator<UniqueKey, Serializable> {

  @Autowired
  private SessionFactory sessionFactory;

  private String[] uniqueIdentifierNames;
  private String objectIdentifierName;
  private Class<?> clazz;

  @Override
  public void initialize(UniqueKey constraintAnnotation) {
    this.uniqueIdentifierNames = constraintAnnotation.columnNames();
    clazz                      = constraintAnnotation.clazzName();
    this.objectIdentifierName  = constraintAnnotation.objectIdentifierName();
  }

  @Override
  public boolean isValid(Serializable target, ConstraintValidatorContext context) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      Criteria criteria = session.createCriteria(clazz);
      try {
        for (String propertyName : uniqueIdentifierNames) {
          PropertyDescriptor desc = new PropertyDescriptor(propertyName, target.getClass());
          Method readMethod = desc.getReadMethod();
          Object propertyValue = readMethod.invoke(target);
          criteria.add(Restrictions.eq(propertyName, propertyValue));
        }

        PropertyDescriptor desc = new PropertyDescriptor(objectIdentifierName, target.getClass());
        Method readMethod = desc.getReadMethod();
        Object propertyValue = readMethod.invoke(target);
        if (propertyValue!=null){
          criteria.add(Restrictions.ne(objectIdentifierName, propertyValue));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      return criteria.list().size() == 0;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
}
