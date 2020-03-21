package com.makao.memo.persistance;

import java.util.List;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<MemoTodo> getTodoList(Long memoId) {
		String jpql = "SELECT t.complete, t.id, t.title FROM MemoTodo t where t.memo.id = :memoId";
		Query query = getSession().createQuery(jpql);
		query.setParameter("memoId", memoId);
		return query.list();
	}
}
