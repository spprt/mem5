package com.makao.memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.makao.memo.entity.Memo;
import com.makao.memo.service.MemoService;
import com.makao.memo.util.AuthInfo;

@Controller
public class MemoController {
	private static final Logger logger = Logger.getLogger(MemoController.class);

	public MemoController() {
		if (logger.isInfoEnabled()) {
			logger.info("MemoController()");
		}
	}

	@Autowired
	private MemoService service;

	@RequestMapping(value = "/memo/allMyList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getAllMyMemo(HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		List<Memo> list = service.getAllMemo(authInfo.getId());
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("array", list);
		return resMap;
	}

	@RequestMapping(value = "/memo/myList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getMyMemo(Long id, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		List<Memo> list = service.getCtgrMemo(authInfo.getId(), id);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("array", list);
		return resMap;
	}

	@RequestMapping(value = "/memo/selectType", method = RequestMethod.GET)
	public ModelAndView goAdd(Long ctgrId) throws Exception {
		ModelAndView mv = new ModelAndView("memo/selectType");
		if (null != ctgrId) {
			mv.addObject("ctgrId", ctgrId);
		}
		return mv;
	}

	@RequestMapping(value = "/memo/add", method = RequestMethod.GET)
	public ModelAndView goAdd(Long ctgrId, int type) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (null != ctgrId) {
			mv.addObject("ctgrId", ctgrId);
		}
		if (type == Memo.TYPE_NOTE) {
			mv.setViewName("redirect:/?rightPage=/memo/goAddNote");
		} else if (type == Memo.TYPE_TODO) {
			mv.setViewName("memo/addTodo");
		}
		return mv;
	}

	@RequestMapping(value = "/memo/goAddNote", method = RequestMethod.GET)
	public String goAddNote(Long ctgrId) throws Exception {
		String page = "/memo/addNote";
		if (null != ctgrId) {
			page += "ctgrId=" + ctgrId;
		}

		return page;
	}

	@RequestMapping(value = "/memo/saveNote", method = RequestMethod.POST)
	public ModelAndView saveNote(@ModelAttribute Memo memo, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if (null != authInfo) {
			memo.setRegUserId(authInfo.getId());
		}

		service.addMemo(memo);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}

	@RequestMapping(value = "/memo/view", method = RequestMethod.GET)
	public ModelAndView viewMemo(Long id, HttpSession session) throws Exception {
		Memo memo = service.readMemo(id);
		String viewType = "memo/viewMemo";
		if (memo.getType() == Memo.TYPE_TODO) {
			viewType = "memo/viewTodo";
		}
		ModelAndView mv = new ModelAndView(viewType);
		mv.addObject("memo", memo);
		return mv;
	}
}
