package com.muletut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.muletut.dto.SearchForm;

@Controller
public class PageNotFoundErrorController {

	@RequestMapping(value = "/404.html")
	public String handlePageNotFound(ModelMap model) {
		SearchForm searchForm = new SearchForm();
		model.addAttribute("search", searchForm);
		return "404";
	}
}
