package gr.codehunters.MovieLibrary.controller;


import gr.codehunters.MovieLibrary.exceptions.UserNotFoundException;
import gr.codehunters.MovieLibrary.model.dto.users.AddressEntityListDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.PasswordEntityDTO;
import gr.codehunters.MovieLibrary.model.dto.users.SecurityRoleEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.dto.users.UserEntityDTOImpl;
import gr.codehunters.MovieLibrary.model.menu.Page;
import gr.codehunters.MovieLibrary.service.PageService;
import gr.codehunters.MovieLibrary.service.RolesService;
import gr.codehunters.MovieLibrary.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
  @Autowired
  UserService userService;
  @Autowired
  RolesService rolesService;
  @Autowired
  PageService pageService;

  @PostConstruct
  public void init() {
    pageService.add(new Page("Users", "Home", "/users"));
    pageService.add(new Page("User Details", "Users", "/users/user/details"));
    pageService.add(new Page("User Edit", "Users", "/users/user/edit"));
    pageService.add(new Page("User Edit Address", "Users", "/users/user/address"));
    pageService.add(new Page("User Add", "Users", "/users/user/add"));
    pageService.add(new Page("User Add", "Users", "/users/user/add"));
    pageService.add(new Page("User Edit Password", "Users", "/users/user/password/change"));
    pageService.add(new Page("User Delete", "Users", "/users/user/delete"));
  }

  @RequestMapping(value = "/users/user/details", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public ModelAndView viewUserDetails(final HttpServletRequest request,  ModelAndView modelAndView) throws UserNotFoundException {
    logger.info("requesting /users/user/details {RequestMethod.GET}");
    modelAndView.addObject("user", getUser(request,false));
    modelAndView.setViewName("user_details");
    return modelAndView;
  }

  @RequestMapping(value = {"users/user/edit"}, method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public ModelAndView editUser(final HttpServletRequest request, ModelAndView mv) throws UserNotFoundException {
    logger.info("requesting /users/user/edit {RequestMethod.GET}");
    UserEntityDTOImpl user=getUser(request,false);
    mv.addObject("user", user);
    mv.addObject("genderList", userService.getGenderList());
    mv.addObject("rolesOptionList", rolesService.listRoles());
    mv.setViewName("user_edit");
    return mv;
  }

  @RequestMapping(value = {"users/user/edit"}, method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public ModelAndView editUser(ModelAndView mv, @ModelAttribute("user") @Valid UserEntityDTOImpl user, BindingResult result,final HttpServletRequest request) {
    logger.info("requesting /users/user/edit {RequestMethod.POST}");
    if (result.hasErrors()) {
      logger.info("User edit validation errors found, responding with validation result");
      mv.setViewName("user_edit");
      mv.addObject("user", user);
      mv.addObject("genderList", userService.getGenderList());
      mv.addObject("rolesOptionList", rolesService.listRoles());
      return mv;
    } else {
      logger.info("Saving user updated information");
      Long id = userService.save(user).getId();
      mv.setViewName("user_details");
      mv.addObject("Id", id);
    }
    return mv;
  }


  @RequestMapping(value = "/users/user/address", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public ModelAndView getAddress(final HttpServletRequest request) throws UserNotFoundException {
    logger.info("requesting /users/user/address {RequestMethod.GET}");
    String id = request.getParameter("id");
    AddressEntityListDTOImpl addressEntityListDTO;
    if (id != null && id.length() > 0) {
      addressEntityListDTO=userService.getAddressEntityList(id);
    } else {
      throw new UserNotFoundException();
    }
    return new ModelAndView("add_address" , "addressList", addressEntityListDTO);
  }

  @RequestMapping(value = "/users/user/address", method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public ModelAndView addAddress(@RequestParam String action,@ModelAttribute("addressList") AddressEntityListDTOImpl addressEntityListDTO,final HttpServletRequest request) throws UserNotFoundException {
    logger.info("requesting /users/user/address {RequestMethod.POST, action "+action+"}");
    if( action.equals("Save") ){
      return new ModelAndView("show_address", "addressList", userService.save(addressEntityListDTO));
    }else if( action.equals("Add") ){
      return new ModelAndView("add_address", "addressList", userService.addNewAdress(addressEntityListDTO));
    }
    return new ModelAndView("show_address", "addressList", addressEntityListDTO);
  }

  @RequestMapping(value = "/users/user/address/delete")
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public String removeAddress(@ModelAttribute("addressList") AddressEntityListDTOImpl addressEntityListDTO,final HttpServletRequest request) throws UserNotFoundException {
    logger.info("requesting /users/user/address/delete");
    String index = request.getParameter("id");
    if (index != null && index.length() > 0) {
      addressEntityListDTO.getAddressEntityDTOs().remove(Integer.parseInt(index));
    }
    return "redirect:/users/user/address?id="+addressEntityListDTO.getId();
  }




  @RequestMapping(value = "/users/user/add", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView addUser(final HttpServletRequest request, ModelAndView mv, @ModelAttribute("user") UserEntityDTOImpl user) throws UserNotFoundException {
    logger.info("requesting /users/user/commit {RequestMethod.GET}");
    mv.addObject("user", getUser(request,true));
    mv.addObject("genderList", userService.getGenderList());
    mv.addObject("rolesOptionList", rolesService.listRoles());
    mv.setViewName("user_add");
    return mv;
  }

  @RequestMapping(value = {"/users/user/add"}, method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView addUser(ModelAndView mv, @ModelAttribute("user") @Valid UserEntityDTOImpl user, BindingResult result) {
    logger.info("requesting /users/user/commit {RequestMethod.POST}");
    if (result.hasErrors()) {
      mv.setViewName("user_add");
      mv.addObject("user", user);
      mv.addObject("genderList", userService.getGenderList());
      mv.addObject("rolesOptionList", rolesService.listRoles());
      return mv;
    } else {
      Long id = userService.save(user).getId();
      mv.setViewName("user_details");
      mv.addObject("Id", id);
    }
    return mv;
  }

  @RequestMapping(value = "/users/user/password/change", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public ModelAndView passwordChange(final HttpServletRequest request, ModelAndView mv, @ModelAttribute("passwordCommand") PasswordEntityDTO passwordEntity) throws UserNotFoundException {
    logger.info("requesting /users/user/password/change {RequestMethod.GET}");
    String id = request.getParameter("id");
    if (id != null && id.length() > 0) {
      passwordEntity = userService.getPasswordEntityrByUserId(Long.parseLong(id));
    }
    mv.addObject("passwordEntity", passwordEntity);
    mv.setViewName("user_password");
    return mv;
  }

  @RequestMapping(value = {"/users/user/password/change"}, method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public ModelAndView passwordChange(ModelAndView mv, @ModelAttribute("passwordEntity") @Valid PasswordEntityDTO passwordEntity, BindingResult result,final HttpServletRequest request) throws UserNotFoundException {
    logger.info("requesting /users/user/password/change {RequestMethod.POST}");
    if (result.hasErrors()) {
      mv.setViewName("user_password");
      mv.addObject("passwordEntity", passwordEntity);
      return mv;
    } else {
      Long id = userService.save(passwordEntity).getId();
      mv.setViewName("user_details");
      mv.addObject("user", userService.getUserById(id));
      mv.addObject("Id", id);
    }
    return mv;
  }

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public ModelAndView viewUsers(ModelAndView mv) {
    logger.info("requesting /users/ {RequestMethod.GET}");
    mv.addObject("users", userService.loadUsers());
    mv.setViewName("users_table");
    return mv;
  }

  @RequestMapping(value = "/users/user/delete")
  @PreAuthorize("hasRole('ADMINISTRATOR') or hasPermission(#request, 'isCurrentUser')")
  public String deleteUser(final HttpServletRequest request) {
    logger.info("requesting /users/user/delete");
    String id = request.getParameter("id");
    userService.deleteUserById(Long.parseLong(id));
    logger.info("requesting/delete user:" + id);
    if (request.isUserInRole("ADMINISTRATOR")){
       return "redirect:/users";
    }else {
      return "redirect:/";
    }
  }


  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Set.class, "userSecurityRoleEntity", new CustomCollectionEditor(Set.class) {
      @Override
      protected Object convertElement(Object element) {
        List<Long> id = new ArrayList<Long>();
        List<String> roleName = new ArrayList<String>();
        List<SecurityRoleEntityDTOImpl> roles = new ArrayList<SecurityRoleEntityDTOImpl>();
        if (element instanceof String && !(element).equals("")) {
          String[] elements = ((String) element).split(",");
          for (String elem : elements) {
            try {
              id.add(Long.parseLong(elem));
            } catch (Exception ex) {
              roleName.add(elem);
            }
          }
        } else if (element instanceof Long) {
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

  private UserEntityDTOImpl getUser(final HttpServletRequest request,boolean createNewIfUserDoesNotExist) throws UserNotFoundException {
    UserEntityDTOImpl userEntityDTO=null;
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    if (id != null && id.length() > 0) {
      userEntityDTO = (UserEntityDTOImpl) userService.getUserById(Long.parseLong(id));
    } else if (name != null && name.length() > 0) {
      userEntityDTO = (UserEntityDTOImpl) userService.getUserByUsername(name);
    }
    if (userEntityDTO==null ){
      if (createNewIfUserDoesNotExist){
        return new UserEntityDTOImpl();
      }else {
      throw new UserNotFoundException();
      }
    }
    return userEntityDTO;
  }

}


