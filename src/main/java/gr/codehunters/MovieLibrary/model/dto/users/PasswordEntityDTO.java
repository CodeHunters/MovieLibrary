package gr.codehunters.MovieLibrary.model.dto.users;

import gr.codehunters.MovieLibrary.model.Identifiable;
import gr.codehunters.MovieLibrary.model.Nameable;
import gr.codehunters.MovieLibrary.validator.ContentMatch;
import gr.codehunters.MovieLibrary.validator.PasswordMatch;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ContentMatch(fields = {"password","confirmPassword"},message = "Passwords do not match")
@PasswordMatch(message = "Incorrect Password entries")
public class PasswordEntityDTO implements Identifiable<Long>,Nameable<String>,Serializable {
  Long id;
  String name;
  String oldPassword;
  @Pattern(regexp = "(((?=.*\\d)((?=.*[a-z])|(?=.*[A-Z]))(?=.*[@#$%!]).{6,20})|PREDEFINED)",message = "User password is too weak")
  String password;
  String confirmPassword;

  public PasswordEntityDTO() {
  }

  public PasswordEntityDTO(Long id,String userName) {
    this.id=id;
    this.name=userName;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id=id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name=name;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
