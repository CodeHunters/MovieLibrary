/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.validators;
import gr.codehunters.MovieLibrary.model.db.users.UserEntityDBImpl;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		return UserEntityDBImpl.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"required.confirmPassword", "Field name is required.");

    UserEntityDBImpl userEntityDB = (UserEntityDBImpl)target;

		if(!(userEntityDB.getPassword().equals(userEntityDB))){
			errors.rejectValue("password", "notmatch.password");
		}

	}

}