package com.makao.memo.persistance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.makao.memo.entity.MemoTodo;

@Repository("todoDAO")
public class TodoDAOImpl implements TodoDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTodo(MemoTodo todo) {
		getSession().save(todo);
	}

	@Override
	public void delTodo(Long todoId) {
		MemoTodo todo = (MemoTodo) getSession().get(MemoTodo.class, todoId);
		getSession().delete(todo);
	}

	@Override
	public void checkTodo(Long todoId, boolean checked) {
		MemoTodo todo = (MemoTodo) getSession().get(MemoTodo.class, todoId);
		todo.setComplete(checked);
		getSession().update(todo);
	}

}
