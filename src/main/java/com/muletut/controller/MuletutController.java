package com.muletut.controller;

import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriUtils;

import com.muletut.dto.SearchForm;
import com.muletut.exceptions.MuletutException;
import com.muletut.service.MuletutService;

@Controller
public class MuletutController {
	@Autowired
	MuletutService muletutService;

	/**
	 * Method to get index menu items
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String getMenu(ModelMap model, HttpServletRequest request) {
		return getIndexMenu(model, request);

	}

	@RequestMapping("/index.html")
	public String getIndex(ModelMap model, HttpServletRequest request) {
		return getIndexMenu(model, request);
	}

	private String getIndexMenu(ModelMap model, HttpServletRequest request) {

		ArrayList<String> menuItems;
		SearchForm searchForm = new SearchForm();
		model.addAttribute("search", searchForm);
		try {
			if (muletutService.addMenuItems()) {
				menuItems = muletutService.getIndexMenu();
				model.addAttribute("menuItems", menuItems);
				return "index";
			} else {
				return "redirect: error.html";
			}
		} catch (MuletutException e) {
			e.printStackTrace();
			return "redirect: error.html";
		}
	}

	/**
	 * Method to get tutorial
	 * 
	 * @param request
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/tut")
	public String getTutData(HttpServletRequest request) {
		String title = request.getParameter("title");
		String fileData = null;
		try {
			fileData = muletutService.readFile(title);
		} catch (MuletutException e) {
			e.printStackTrace();
		}
		return fileData;

	}

	/**
	 * Method to fetch reference page and reference menu items
	 * 
	 * @return
	 */
	@RequestMapping("/references.html")
	public String getRefer(ModelMap model, HttpServletRequest request) {
		ArrayList<String> menuItems;
		try {
			SearchForm searchForm = new SearchForm();
			if (muletutService.addReferenceMenuItems()) {
				menuItems = muletutService.getReferenceMenu();
				model.addAttribute("search", searchForm);
				model.addAttribute("menuItems", menuItems);
				return "references";
			} else {
				return "redirect:error.html";
			}
		} catch (MuletutException e) {
			e.printStackTrace();
			return "redirect:error.html";
		}
	}

	/**
	 * Method to fetch cloudhub page and cloud menu items
	 * 
	 * @return
	 */
	@RequestMapping("/cloudhub.html")
	public String getCloud(ModelMap model, HttpServletRequest request) {
		ArrayList<String> menuItems;
		try {
			SearchForm searchForm = new SearchForm();
			if (muletutService.addMenuItems()) {
				menuItems = muletutService.getIndexMenu();
				model.addAttribute("search", searchForm);
				model.addAttribute("menuItems", menuItems);
				return "cloudhub";
			} else {
				return "redirect: error.html";
			}
		} catch (MuletutException e) {
			e.printStackTrace();
			return "redirect: error.html";
		}
	}

	/**
	 * Method to fetch blog home page
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/blog.html")
	public String getBlog(ModelMap model, HttpServletRequest request) {
		ArrayList<String> posts;
		try {
			SearchForm searchForm = new SearchForm();
			if (muletutService.addBlogPosts()) {
				posts = muletutService.getBlogPosts();
				model.addAttribute("search", searchForm);
				model.addAttribute("posts", posts);
				return "blog";
			} else {
				return "redirect: error.html";
			}
		} catch (MuletutException e) {
			e.printStackTrace();
			return "redirect: error.html";
		}
	}

	/**
	 * Method to fetch post page
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("*.htm")
	public String getPost(ModelMap model, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String postName = path.split("\\.")[0];
		ArrayList<String> posts;
		try {
			SearchForm searchForm = new SearchForm();
			posts = muletutService.getBlogPostsForSingle(postName);
			model.addAttribute("search", searchForm);
			model.addAttribute("posts", posts);
			model.addAttribute("postName", postName);
			model.addAttribute("url", URLEncoder.encode(url));
			return "single";
		} catch (MuletutException e) {
			e.printStackTrace();
			return "redirect: error.html";
		}
	}

	/**
	 * Method to get post
	 * 
	 * @param request
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/post")
	public String getPostData(HttpServletRequest request) {
		String title = request.getParameter("title");
		String fileData = null;
		try {
			fileData = muletutService.readPost(title);
		} catch (MuletutException e) {
			e.printStackTrace();
		}
		return fileData;

	}

	/**
	 * Method to fetch about page
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/about.html")
	public String getAbout(ModelMap model, HttpServletRequest request) {
		ArrayList<String> menuItems;
		try {
			SearchForm searchForm = new SearchForm();
			if (muletutService.addMenuItems()) {
				menuItems = muletutService.getIndexMenu();
				model.addAttribute("search", searchForm);
				model.addAttribute("menuItems", menuItems);
				return "about";
			} else {
				return "redirect: error.html";
			}
		} catch (MuletutException e) {
			e.printStackTrace();
			return "redirect: error.html";
		}
	}

	/**
	 * Method to search
	 * 
	 * @param searchForm
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/search")
	public String search(@ModelAttribute("search") SearchForm searchForm, ModelMap model) {
		String searchString = searchForm.getSearchString();
		try {
			muletutService.createIndex();
			ArrayList<String> matchedTitles = muletutService.search(searchString);
			model.addAttribute("search", searchForm);
			model.addAttribute("searchString", searchString);
			model.addAttribute("titles", matchedTitles);
			System.out.println(matchedTitles);
			return "search";
		} catch (MuletutException e) {
			e.printStackTrace();
			return "redirect: error.html";
		}
	}

	/**
	 * Method to fetch error page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/error.html")
	private String getError(ModelMap model) {
		SearchForm searchForm = new SearchForm();
		model.addAttribute("search", searchForm);
		return "error";
	}

}
