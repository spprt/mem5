package com.makao.memo.persistance;

import java.util.List;

import com.makao.memo.entity.MemoTodo;

public interface TodoDAO {
	public void addTodo(MemoTodo todo);

	public void delTodo(Long todoId);

	public void checkTodo(Long todoId, boolean checked);
	
	public List<MemoTodo> getTodoList(Long memoId);
}
