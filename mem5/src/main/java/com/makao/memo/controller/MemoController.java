package com.makao.memo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.makao.memo.entity.Memo;
import com.makao.memo.service.MemoService;
import com.makao.memo.util.AuthInfo;

@Controller
public class MemoController
{
	private static final Logger logger = Logger.getLogger(MemoController.class);
	
	public MemoController() {
		if (logger.isInfoEnabled()) {
			logger.info("MemoController()");
		}
	}

	@Autowired
	private MemoService service;
	
	@RequestMapping(value = "/memo/allMyList")
	public ModelAndView getAllMyMemo(Model model, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		ModelAndView mv = new ModelAndView("memo/memoList");
		if (null != authInfo) {
			List<Memo> list = service.getAllMemo(authInfo.getId());
			mv.addObject("list", list);
		}
		return mv;
	}
	
//	@RequestMapping(value = "/memo/allMyList", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public Map<String,Object> getAllMyMemo(HttpSession session) throws Exception {
//		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
//		List<Memo> list = service.getAllMemo(authInfo.getId());
//		Map<String,Object> resMap = new HashMap<String, Object>();
//		resMap.put("array", list);
//		System.out.println(list.size());
//		return resMap;
//	}
	
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
			mv.setViewName("memo/addNote");
		} else if(type==Memo.TYPE_TODO) {
			mv.setViewName("memo/addTodo");
		}
		return mv;
	}
	
	

}
