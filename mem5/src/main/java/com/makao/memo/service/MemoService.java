package com.makao.memo.service;

import java.util.List;

import com.makao.memo.entity.Memo;
import com.makao.memo.entity.MemoShare;
import com.makao.memo.entity.MemoTodo;

public interface MemoService {

	public void addMemo(Memo memo, MemoShare share);

	public void updateMemo(Memo memo);

	public void delMemo(Long memoId);

	public Memo readMemo(Long memoId);

	public List<Memo> getAllMemo(Long userId);

	public List<Memo> getCtgrMemo(Long userId, Long ctgrId);
	
	public List<Memo> getPtlList(Long userId);

	public void addTodo(MemoTodo todo);

	public void checkTodo(Long todoId, boolean checked);

	public void allCheckTodo(Long memoId, boolean checked);

	public void delTodo(Long todoId);
}
