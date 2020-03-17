package com.makao.memo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makao.memo.entity.Memo;
import com.makao.memo.entity.MemoTodo;
import com.makao.memo.service.MemoService;

@Controller
public class TodoController {
	private static final Logger logger = Logger.getLogger(TodoController.class);

	public TodoController() {
		if (logger.isInfoEnabled()) {
			logger.info("TodoController()");
		}
	}

	@Autowired
	private MemoService service;

	@RequestMapping(value = "/memo/todo/add", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> add(Long id, String title) throws Exception {
		Memo memo = service.readMemo(id);
		MemoTodo todo = new MemoTodo();
		todo.setMemo(memo);
		todo.setTitle(title);
		service.addTodo(todo);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("id", todo.getId());
		return resMap;
	}

	@RequestMapping(value = "/memo/todo/check", method = RequestMethod.GET)
	public String check(Long id, boolean check, HttpSession session) throws Exception {
		service.checkTodo(id, check);
		return "";
	}

	@RequestMapping(value = "/memo/todo/allCheck", method = RequestMethod.GET)
	public String allCheck(Long id, boolean check, HttpSession session) throws Exception {
		service.allCheckTodo(id, check);
		return "";
	}

	@RequestMapping(value = "/memo/todo/delete", method = RequestMethod.GET)
	public String delete(Long id, HttpSession session) throws Exception {
		service.delTodo(id);
		return "";
	}
}
