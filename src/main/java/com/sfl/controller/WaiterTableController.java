package com.sfl.controller;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */
import javax.validation.Valid;

import com.sfl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sfl.model.WaiterTable;
import com.sfl.service.TableService;
import com.sfl.service.UserService;
import com.sfl.service.WaiterTableService;

@Controller
@RequestMapping("/manager/waiter-tables")
public class WaiterTableController {


	@Autowired
	private UserService userService;

	@Autowired
	private WaiterTableService waiterTableService;

	@Autowired
	private TableService tableService;


	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUserTable() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("waiter_table", new WaiterTable());
		modelAndView.addObject("users", userService.findAllWaiters());
		modelAndView.addObject("tables", tableService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("waiter_table");
		return modelAndView;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allUserTables() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new WaiterTable());
		modelAndView.addObject("waiter_table", waiterTableService.findAll());
		modelAndView.addObject("users", userService.findAllWaiters());
		modelAndView.addObject("tables", tableService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("waiter_table");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUserTable(@Valid WaiterTable waiterTable, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("redirect:/manager/waiter-tables/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		waiterTableService.save(waiterTable);
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateUserTable(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new WaiterTable());
		modelAndView.addObject("waiter_table", waiterTableService.findUserTable(id));
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("tables", tableService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("waiter_table");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUserTable(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/manager/waiter-tables/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		waiterTableService.delete(id);
		return modelAndView;
	}

	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}

}
