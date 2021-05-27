package com.sfl.controller;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */

import com.sfl.model.Order;
import com.sfl.model.User;
import com.sfl.service.OrderService;
import com.sfl.service.ProductService;
import com.sfl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newOrder() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", new Order());
        modelAndView.addObject("orders", orderService.findAll());
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_NEW");
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveOrder(@Valid Order order, BindingResult bindingResult) {
        order.setDateCreated(new Date());
        orderService.save(order);
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/all");
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        return modelAndView;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allOrders() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rule", new Order());
        modelAndView.addObject("orders", orderService.findAll());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_ALL");
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateOrder(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rule", new Order());
        modelAndView.addObject("order", orderService.findOrder(id));
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_UPDATE");
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/all");
        modelAndView.addObject("rule", new Order());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        orderService.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/per_inf", method = RequestMethod.GET)
    public ModelAndView infOrder(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rule", new Order());
        modelAndView.addObject("order", orderService.findOrder(id));
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_INF");
        modelAndView.setViewName("order");
        return modelAndView;
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user;
    }
}
