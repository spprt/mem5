package com.makao.memo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.makao.memo.entity.Memo.Tag;
import com.makao.memo.entity.MemoShare;
import com.makao.memo.entity.User;
import com.makao.memo.service.CategoryService;
import com.makao.memo.service.MemoService;
import com.makao.memo.service.UserService;
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

	@Autowired
	private UserService userSer;

	@Autowired
	private CategoryService ctgrService;

	@RequestMapping(value = "/memo/main", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView goMain(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("memo/main");
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if (null != authInfo) {
			List<Memo> list = service.getPtlList(authInfo.getId());
			mv.addObject("list", list);
		}
		return mv;
	}

	@RequestMapping(value = "/memo/allMyList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getAllMyMemo(HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (null != authInfo) {
			List<Memo> list = service.getAllMemo(authInfo.getId());
			resMap.put("array", list);
		}
		return resMap;
	}

	@RequestMapping(value = "/memo/myList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getMyMemo(Long ctgrId, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		List<Memo> list = service.getCtgrMemo(authInfo.getId(), ctgrId);
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
		// redirect시 ctgrId 재확인 필요
		if (type == Memo.TYPE_NOTE) {
			mv.setViewName("redirect:/?rightPage=/memo/goAddNote?ctgrId=" + ctgrId);
		} else if (type == Memo.TYPE_TODO) {
			mv.setViewName("redirect:/?rightPage=/memo/goAddTodo?ctgrId=" + ctgrId);
		}
		return mv;
	}

	@RequestMapping(value = "/memo/goAddNote", method = RequestMethod.GET)
	public ModelAndView goAddNote(Long ctgrId) throws Exception {
		ModelAndView mv = new ModelAndView("/memo/addNote");
		if (null != ctgrId) {
			mv.addObject("ctgrId", ctgrId);
		}
		return mv;
	}

	@RequestMapping(value = "/memo/goAddTodo", method = RequestMethod.GET)
	public ModelAndView goAddTodo(Long ctgrId) throws Exception {
		ModelAndView mv = new ModelAndView("/memo/addTodo");
		if (null != ctgrId) {
			mv.addObject("ctgrId", ctgrId);
		}
		return mv;
	}

	@RequestMapping(value = "/memo/saveNote", method = RequestMethod.POST)
	public ModelAndView saveNote(@ModelAttribute Memo memo, Long ctgrId, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if (null != authInfo) {
			memo.setRegUserId(authInfo.getId());
		}
		Set<MemoShare> shares = new HashSet<MemoShare>();
		User register = userSer.getUser(memo.getRegUserId());

		MemoShare ms = new MemoShare(register);
		ms.setRegister(true);
		ms.setCtgrId(ctgrId);
		shares.add(ms);

		service.addMemo(memo, ms);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}

	@RequestMapping(value = "/memo/view", method = RequestMethod.GET)
	public ModelAndView viewMemo(Long id, HttpSession session) throws Exception {
		Memo memo = service.readMemo(id);
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String viewType = "memo/viewMemo";
		if (memo.getType() == Memo.TYPE_TODO) {
			viewType = "memo/viewTodo";
		}
		// 분류명 가져오기
		List<MemoShare> shares = memo.getShares().stream()
				.filter(ms -> ms.getMemo().getId() == id && ms.getUserId() == authInfo.getId())
				.collect(Collectors.toList());
		Long ctgrId = -1L;
		if (!shares.isEmpty()) {
			ctgrId = shares.get(0).getCtgrId();
		}

		String ctgrName = "";
		if (ctgrId != -1L) {
			ctgrName = ctgrService.getCategory(ctgrId).getCtgrName();
		}

		ModelAndView mv = new ModelAndView(viewType);
		mv.addObject("memo", memo);
		mv.addObject("ctgrName", ctgrName);
		return mv;
	}

	@RequestMapping(value = "/memo/goEditNote", method = RequestMethod.GET)
	public ModelAndView goEdit(Long id, HttpSession session) throws Exception {
		Memo memo = service.readMemo(id);
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		ModelAndView mv = new ModelAndView();
		if (null != authInfo && authInfo.getId() == memo.getRegUserId()) {
			mv.addObject("memo", memo);
			List<Tag> tags = memo.getTags();
			String tagStr = tags.stream().map(t -> t.getTag()).collect(Collectors.joining(","));
			mv.addObject("tagStr", tagStr);
//			mv.setViewName("redirect:/?rightPage=/memo/editNote");
			mv.setViewName("memo/editNote");
		} else {
			mv.setViewName("redirect:/");
		}

		return mv;
	}

	@RequestMapping(value = "/memo/saveEditNote", method = RequestMethod.POST)
	public ModelAndView editNote(@ModelAttribute Memo memo, HttpSession session) throws Exception {
//		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		memo.setModDate(new Date());
		service.updateMemo(memo);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}

	@RequestMapping(value = "/memo/move", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> move(Long memoId, Long ctgrId, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		Long authId = authInfo.getId();
		Memo memo = service.readMemo(memoId);
		List<MemoShare> shares = memo.getShares().stream()
				.filter(s -> s.getUserId() == authId && s.getMemo().getId() == memoId).collect(Collectors.toList());
		Map<String, String> result = new HashMap<String, String>();
		if(shares.size() > 0) {
			MemoShare ms = shares.get(0);
			if (ms.getCtgrId() != ctgrId) {
				ms.setCtgrId(ctgrId);
				service.updateShare(ms);
				result.put("result", "success");
			} else {
				result.put("result", "same Category");
			}
		} else {
			User register = userSer.getUser(authId);
			MemoShare ms = new MemoShare(register);
			ms.setRegister(memo.getRegUserId() == authId);
			ms.setCtgrId(ctgrId);
			ms.setMemo(memo);
			service.updateShare(ms);
			result.put("result", "success");
		}
		
		return result;
	}
}
