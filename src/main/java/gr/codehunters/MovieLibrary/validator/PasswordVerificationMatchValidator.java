package gr.codehunters.MovieLibrary.validator;

import gr.codehunters.MovieLibrary.controller.MovieController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PasswordVerificationMatchValidator implements ConstraintValidator<ContentMatch, Serializable> {
  private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

   String[] fields;

  @Override
  public void initialize(ContentMatch constraintAnnotation) {
   fields=constraintAnnotation.fields();
  }

  @Override
  public boolean isValid(Serializable target, ConstraintValidatorContext context) {
    List<Object> contentFieldValues=new ArrayList<Object>();
    for (String field : fields) {
      try{
      PropertyDescriptor desc = new PropertyDescriptor(field, target.getClass());
      Method readMethod = desc.getReadMethod();
      Object propertyValue = readMethod.invoke(target);
      contentFieldValues.add(propertyValue);
      }catch (Exception ex){
        logger.error("Error while checking if password is valid with reason",ex);
      }
    }
    boolean contentsMatch=true;
    Iterator<Object> items=contentFieldValues.iterator();
    Object refObject=null;
    if (items.hasNext()){
       refObject=items.next();
    }
    while (contentsMatch && items.hasNext())             {
      Object o=items.next();
      if (o==null){
        if (refObject!=null){
          contentsMatch=false;
        }
      }else {
         contentsMatch=o.equals(refObject);
      }
    }
    return contentsMatch;
  }
}
