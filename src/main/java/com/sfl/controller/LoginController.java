package com.sfl.controller;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */

import javax.validation.Valid;

import com.sfl.model.Role;
import com.sfl.model.Table;
import com.sfl.model.WaiterTable;
import com.sfl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sfl.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TableService tableService;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private WaiterTableService waiterTableService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Email has already been taken"
                                    + " Check your details!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Registration Successful.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }


    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("403");
        return modelAndView;
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Role role = new Role();
        Role role2 = new Role();
        role = roleService.findRole("MANAGER");
        role2 = roleService.findRole("WAITER");
        List<User> users = new ArrayList<>();
        List<User> users2 = new ArrayList<>();
        users = userService.findUserbyRole(role);
        users2 = userService.findUserbyRole(role2);
        int tableCount = tableService.findAll().size();
        int productCount = productService.findAll().size();
        int orderCount = orderService.findAll().size();
        int managerCount = users.size();
        int userCount = users2.size();
        modelAndView.addObject("managerCount", managerCount);//Authentication for NavBar
        modelAndView.addObject("userCount", userCount);//Authentication for NavBar
        modelAndView.addObject("tableCount", tableCount);//Authentication for NavBar
        modelAndView.addObject("productCount", productCount);//Authentication for NavBar
        modelAndView.addObject("orderCount", orderCount);//Authentication for NavBar
        //-----------------------------------------
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loginUser = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("control", loginUser.getRole().getRole());//Authentication for NavBar
        modelAndView.addObject("auth", loginUser);
        List<WaiterTable> waiterTables = new ArrayList<>();
        waiterTables = waiterTableService.findByUser(loginUser);
        modelAndView.addObject("userTableSize", waiterTables.size());
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
