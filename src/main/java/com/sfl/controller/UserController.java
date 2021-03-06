package com.sfl.controller;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */

import javax.validation.Valid;

import com.sfl.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sfl.model.User;

import com.sfl.service.RoleService;
import com.sfl.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("mode", "MODE_ALL");
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_NEW");
        modelAndView.setViewName("user");
        return modelAndView;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/users/all");
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Email has already been taken"
                                    + " Check your details!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("rule", new User());
            modelAndView.addObject("successMessage", "Registration Successful.");
            modelAndView.setViewName("user");

        }
        user.setPassword(userService.findUser(user.getId()).getPassword());
        user.setActive(userService.findUser(user.getId()).getActive());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        userService.save(user);
        return modelAndView;
    }
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateUser(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rule", new User());
        modelAndView.addObject("user", userService.findUser(id));
        modelAndView.addObject("roles", roleService.findAll());
        modelAndView.addObject("mode", "MODE_UPDATE");
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.setViewName("user");
        return modelAndView;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/users/all");
        modelAndView.addObject("rule", new User());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        userService.delete(id);
        return modelAndView;
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user;
    }
}







