package gr.codehunters.MovieLibrary.controller;

import gr.codehunters.MovieLibrary.model.users.UserEntityDBImpl;
import gr.codehunters.MovieLibrary.service.RolesServiceImpl;
import gr.codehunters.MovieLibrary.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;
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
    UserEntityDBImpl user = null;
    if (id != null && id.length() > 0) {
      user = userDetailsService.getUserById(Integer.parseInt(id));
    } else if (name != null && name.length() > 0) {
      user = userDetailsService.getUserByUsername(name);
    }
    modelAndView.addObject("user", user);
    modelAndView.setViewName("user_details");
    logger.info("requesting /users");
    return modelAndView;
  }

  @RequestMapping(value = "/users/user/add", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView view(ModelAndView mv, @ModelAttribute("user") UserEntityDBImpl user) {
    mv.addObject("user", user);
    AddGenderList(mv);
    mv.addObject("rolesOptionList", rolesService.listRoles());
    mv.setViewName("user_add");
    logger.info("requesting/new user");
    return mv;
  }

  @RequestMapping(value = "/users/user/add", method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public String update(ModelAndView mv, @ModelAttribute("user") UserEntityDBImpl user) {
    logger.info("updating /user");
    return "redirect:/users/user/details?id=" + userDetailsService.save(user).getId();
  }

  private void AddGenderList(ModelAndView mv) {
    Map<String, String> genderList = new LinkedHashMap<String, String>();
    genderList.put("Male", "Male");
    genderList.put("Female", "Female");
    mv.addObject("genderList", genderList);
  }

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Set.class, "userSecurityRoleEntity", new CustomCollectionEditor(Set.class) {
      @Override
      protected Object convertElement(Object element) {
        Long id = null;

        if (element instanceof String && !((String) element).equals("")) {
          //From the JSP 'element' will be a String
          try {
            id = Long.parseLong((String) element);
          } catch (NumberFormatException e) {
            System.out.println("Element was " + ((String) element));
            e.printStackTrace();
          }
        } else if (element instanceof Long) {
          //From the database 'element' will be a Long
          id = (Long) element;
        }

        return id != null ? rolesService.loadRoleById(id) : null;
      }
    });
  }
}


