package gr.codehunters.MovieLibrary.controller;

import gr.codehunters.MovieLibrary.model.dto.users.PasswordEntityDTO;
import gr.codehunters.MovieLibrary.model.dto.users.SecurityRoleEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import gr.codehunters.MovieLibrary.service.RolesServiceImpl;
import gr.codehunters.MovieLibrary.service.UserDetailsServiceImpl;
import gr.codehunters.MovieLibrary.validator.PasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
  @Autowired
  UserDetailsServiceImpl userDetailsService;
  @Autowired
  RolesServiceImpl rolesService;

  @RequestMapping(value = "/users/user/details", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView view(final HttpServletRequest request, final HttpServletResponse response) {
    ModelAndView modelAndView = new ModelAndView();
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    UserEntityDTOImpl user = null;
    if (id != null && id.length() > 0) {
      user = userDetailsService.getUserById(Long.parseLong(id));
    } else if (name != null && name.length() > 0) {
      user = userDetailsService.getUserByUsername(name);
    }
    modelAndView.addObject("user", user);
    modelAndView.setViewName("user_details");
    logger.info("requesting /users");
    return modelAndView;
  }

  @RequestMapping(value = {"users/user/edit"}, method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView edit(final HttpServletRequest request, ModelAndView mv) {
    UserEntityDTOImpl user;
    String id = request.getParameter("id");
    if (id != null && id.length() > 0) {
      user = userDetailsService.getUserById(Long.parseLong(id));
    } else {
      user = new UserEntityDTOImpl();
      user = userDetailsService.save(user);
    }
    mv.addObject("user", user);
    mv.addObject("genderList", userDetailsService.getGenderList());
    mv.addObject("rolesOptionList", rolesService.addRoleList(user));
    mv.setViewName("user_edit");
    logger.info("requesting/new user");
    return mv;
  }

  @RequestMapping(value = {"users/user/edit"}, method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView edit(ModelAndView mv, @ModelAttribute("user") @Valid UserEntityDTOImpl user, BindingResult result) {
    logger.info("updating /user");
    if (result.hasErrors()) {
      mv.setViewName("user_edit");
      mv.addObject("user", user);
      mv.addObject("genderList", userDetailsService.getGenderList());
      mv.addObject("rolesOptionList", rolesService.addRoleList(user));
      return mv;
    } else {
      Long id = userDetailsService.save(user).getId();
      mv.setViewName("user_details");
      mv.addObject("Id", id);
    }
    return mv;
  }

  @RequestMapping(value = "/users/user/commit", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView edit(final HttpServletRequest request, ModelAndView mv, @ModelAttribute("user") UserEntityDTOImpl user) {
    String id = request.getParameter("id");
    if (id != null && id.length() > 0) {
      user = userDetailsService.getUserById(Long.parseLong(id));
    }
    mv.addObject("user", user);
    mv.addObject("genderList", userDetailsService.getGenderList());
    mv.addObject("rolesOptionList", rolesService.listRoles());
    mv.setViewName("user_add");
    logger.info("requesting/new user");
    return mv;
  }

  @RequestMapping(value = {"/users/user/commit"}, method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView commit(ModelAndView mv, @ModelAttribute("user") @Valid UserEntityDTOImpl user, BindingResult result) {
    logger.info("updating /user");
    if (result.hasErrors()) {
      mv.setViewName("user_add");
      mv.addObject("user", user);
      mv.addObject("genderList", userDetailsService.getGenderList());
      mv.addObject("rolesOptionList", rolesService.listRoles());
      return mv;
    } else {
      Long id = userDetailsService.save(user).getId();
      mv.setViewName("user_details");
      mv.addObject("Id", id);
    }
    return mv;
  }



  @RequestMapping(value = "/users/user/password/change", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView passwd(final HttpServletRequest request, ModelAndView mv, @ModelAttribute("passwordCommand") PasswordEntityDTO passwordEntity) {
    String id = request.getParameter("id");
    if (id != null && id.length() > 0) {
      passwordEntity = userDetailsService.getPasswordEntityrByUserId(Long.parseLong(id));
    }
    mv.addObject("passwordEntity", passwordEntity);
    mv.setViewName("user_password");
    return mv;
  }

  @RequestMapping(value = {"/users/user/password/change"}, method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView passwd(ModelAndView mv, @ModelAttribute("passwordEntity") @Valid PasswordEntityDTO passwordEntity, BindingResult result) {
    logger.info("updating /user");
    if (result.hasErrors()) {
      mv.setViewName("user_password");
      mv.addObject("passwordEntity", passwordEntity);
      return mv;
    } else {
      Long id = userDetailsService.save(passwordEntity).getId();
      mv.setViewName("user_details");
      mv.addObject("user", userDetailsService.getUserById(id));
      mv.addObject("Id", id);
    }
    return mv;
  }

  /**
   * List users action
   *
   * @param mv model/view object
   * @return mv model/view object
   */
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView viewUsers(ModelAndView mv) {

    mv.addObject("users", userDetailsService.loadUsers());
    mv.setViewName("users_table");
    logger.info("requesting/users");
    return mv;
  }

  /**
   * User Delete action
   *
   * @param request request object
   * @return redirect view
   */
  @RequestMapping(value = "/users/user/delete")
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public String deleteUser(final HttpServletRequest request) {
    String id = request.getParameter("id");
    userDetailsService.deleteUserById(Long.parseLong(id));
    logger.info("requesting/delete user:" + id);
    return "redirect:/users";
  }


  /**
   * Security Role binder to allow multi select combo box editor functionality on user edit/add pages
   *
   * @param binder web data binder
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Set.class, "userSecurityRoleEntity", new CustomCollectionEditor(Set.class) {
      @Override
      protected Object convertElement(Object element) {
        List<Long> id = new ArrayList<Long>();
        List<String> roleName = new ArrayList<String>();
        List<SecurityRoleEntityDTOImpl> roles = new ArrayList<SecurityRoleEntityDTOImpl>();
        if (element instanceof String && !(element).equals("")) {
          //From the JSP 'element' will be a String
          String[] elements = ((String) element).split(",");
          for (String elem : elements) {
            try {
              id.add(Long.parseLong(elem));
            } catch (Exception ex) {
              roleName.add(elem);
            }
          }
        } else if (element instanceof Long) {
          //From the database 'element' will be a Long
          id.add((Long) element);
        } else if (element instanceof SecurityRoleEntityDTOImpl) {
          roles.add((SecurityRoleEntityDTOImpl) element);
        }
        roles.addAll(rolesService.loadRoleById(id.toArray(new Long[id.size()])));
        roles.addAll(rolesService.loadRoleByName(roleName.toArray(new String[roleName.size()])));
        return roles;
      }
    });
  }
}


