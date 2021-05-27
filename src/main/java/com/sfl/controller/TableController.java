package com.sfl.controller;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */

import java.util.Date;
import javax.validation.Valid;

import com.sfl.model.Table;
import com.sfl.model.User;
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

import com.sfl.service.TableService;
import com.sfl.service.WaiterTableService;

@Controller
@RequestMapping("/manager/tables")
public class TableController {


	@Autowired
	private TableService tableService;
	
	@Autowired
	private WaiterTableService waiterTableService;

	@Autowired
	private UserService userService;

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newTable(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("table", new Table());
		modelAndView.addObject("tables", tableService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("table");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveTable(@Valid Table table, BindingResult bindingResult) {
		table.setDateCreated(new Date());
		tableService.save(table);
		ModelAndView modelAndView = new ModelAndView("redirect:/manager/tables/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		return modelAndView;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allTables() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Table());
		modelAndView.addObject("tables", tableService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("table");
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateTable(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Table());
		modelAndView.addObject("table", tableService.findTable(id));
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control",getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("table");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteTable(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/manager/tables/all");
		modelAndView.addObject("rule", new Table());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		tableService.delete(id);
		return modelAndView;
	}

	@RequestMapping(value = "/per_inf", method = RequestMethod.GET)
	public ModelAndView infTable(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Table());
		modelAndView.addObject("table", tableService.findTable(id));
		modelAndView.addObject("waitertables", waiterTableService.findByTable(tableService.findTable(id)));
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_INF");
		modelAndView.setViewName("table");
		return modelAndView;
	}

	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}
}
