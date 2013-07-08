package gr.codehunters.MovieLibrary.validator;

import gr.codehunters.MovieLibrary.model.dto.users.PasswordEntityDTO;
import gr.codehunters.MovieLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.Serializable;

@Component
public class PasswordValidator implements ConstraintValidator<PasswordMatch, Serializable> {
  @Autowired
  private UserService userService;

  @Override
  public void initialize(PasswordMatch constraintAnnotation) {

  }

  @Override
  public boolean isValid(Serializable target, ConstraintValidatorContext context) {
    String oldPassword;
    String newPassword;
    boolean isValid=false;
    try {
      PasswordEntityDTO passwordEntityDTO=(PasswordEntityDTO)target;
      oldPassword = passwordEntityDTO.getOldPassword();
      newPassword = passwordEntityDTO.getPassword();
      Long id=passwordEntityDTO.getId();
      isValid=oldPassword!=null && newPassword!=null && id!=null &&
        userService.checkUserPassword(id,oldPassword) && !userService.checkUserPassword(id, newPassword);
    } catch (Exception ignore) {

    }

    return isValid;
  }
}
