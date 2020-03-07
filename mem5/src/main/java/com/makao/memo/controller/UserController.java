package com.makao.memo.controller;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.makao.memo.entity.User;
import com.makao.memo.exception.AlreadyExistingEmailException;
import com.makao.memo.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	public UserController() {
		if (logger.isInfoEnabled()) {
			logger.info("UserController()");
		}
	}

	@Autowired
	private UserService userService;

	@RequestMapping("/signup/step1")
	public String step1() throws Exception {
		return "/user/signup/step1";
	}

	@RequestMapping("/signup/step2")
	public ModelAndView step2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree) throws Exception {
		if (!agree) {
			ModelAndView mv = new ModelAndView("user/signup/step1");
			return mv;
		}
		ModelAndView mv = new ModelAndView("user/signup/step2");
		mv.addObject("user", new User());
		return mv;
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public ModelAndView myPage(ModelAndView model) {
		return new ModelAndView("user/mypage");
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		User user = new User();
		model.addObject("user", user);
		model.setViewName("userForm");
		return model;
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute User user, BindingResult bindingResult) {
		try {
			if (user.getId() == null) { // if user id is 0 then creating the
				// user other updating the user
				userService.addUser(user);
			} else {
				userService.updateUser(user);
			}
		} catch (AlreadyExistingEmailException e) {
			bindingResult.rejectValue("email", "exist", "이미 존재하는 이메일입니다.");
			ModelAndView mv = new ModelAndView("user/signup/step2");
			return mv;
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		userService.deleteUser(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		User user = userService.getUser(id);
		ModelAndView model = new ModelAndView("userForm");
		model.addObject("user", user);

		return model;
	}
}
