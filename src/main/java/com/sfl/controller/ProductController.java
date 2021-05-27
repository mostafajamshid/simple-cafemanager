package com.sfl.controller;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */

import com.sfl.model.Product;
import com.sfl.model.User;
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
@RequestMapping("/manager/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_NEW");
        modelAndView.setViewName("product");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveProduct(@Valid Product product, BindingResult bindingResult) {
        product.setDateCreated(new Date());
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/manager/products/all");
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        return modelAndView;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rule", new Product());
        //POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_ALL");
        modelAndView.setViewName("product");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateProduct(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rule", new Product());
        modelAndView.addObject("product", productService.findProduct(id));
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_UPDATE");
        modelAndView.setViewName("product");
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/manager/products/all");
        modelAndView.addObject("rule", new Product());
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        productService.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/per_inf", method = RequestMethod.GET)
    public ModelAndView infProduct(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rule", new Product());
        modelAndView.addObject("product", productService.findProduct(id));
        modelAndView.addObject("auth", getUser());
        modelAndView.addObject("control", getUser().getRole().getRole());
        modelAndView.addObject("mode", "MODE_INF");
        modelAndView.setViewName("product");
        return modelAndView;
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user;
    }
}
