package com.makao.memo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.makao.memo.entity.Category;
import com.makao.memo.service.CategoryService;
import com.makao.memo.util.AuthInfo;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/category/myList")
	public ModelAndView getMyCategory(Model model, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		ModelAndView mv = new ModelAndView("category/ctgrList");
		if (null != authInfo) {
			List<Category> list = service.getRootCategory(authInfo.getId());
			mv.addObject("list", list);
		}
		return mv;
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.GET)
	public ModelAndView goAdd(Long parentId) throws Exception {
		ModelAndView mv = new ModelAndView("category/add");
		if (null != parentId) {
			mv.addObject("parentId", parentId);
		}
		return mv;
	}

	@RequestMapping(value = "/category/saveAdd", method = RequestMethod.POST)
	public String save(@ModelAttribute Category ctgr, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if (null != authInfo) {
			ctgr.setUserId(authInfo.getId());
			ctgr.setIdx(service.getMaxCount(ctgr.getParentId(), authInfo.getId()));
			service.addCategory(ctgr);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/category/del", method = RequestMethod.GET)
	public String delete(Long id, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		Category ctgr = service.getCategory(id);
		if (ctgr.getUserId().equals(authInfo.getId())) {
			service.delCategory(id);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/category/edit", method = RequestMethod.GET)
	public ModelAndView goEdit(Long id, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("category/edit");
		Category ctgr = service.getCategory(id);
		model.addAttribute("category", ctgr);

		return mv;
	}

	@RequestMapping(value = "/category/saveEdit", method = RequestMethod.POST)
	public ModelAndView edit(Category ctgr, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");

		ModelAndView mv = new ModelAndView();
		if (ctgr.getUserId().equals(authInfo.getId())) {
			service.updateCategory(ctgr);
		}
		mv.setViewName("redirect:/category/myList");
		return mv;
	}

	@RequestMapping(value = "/category/childrenList")
	public List<Category> getChildrenList(Long parentId) throws Exception {
		List<Category> ctgr = service.getChildrenCategory(parentId);

		return ctgr;
	}
}
