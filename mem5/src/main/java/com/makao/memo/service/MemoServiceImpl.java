package com.makao.memo.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.makao.memo.entity.Memo;
import com.makao.memo.entity.MemoShare;
import com.makao.memo.entity.MemoTodo;
import com.makao.memo.persistance.MemoDAO;
import com.makao.memo.persistance.TodoDAO;

@Service("memoService")
@Transactional
public class MemoServiceImpl implements MemoService {
	@Resource(name = "memoDAO")
	private MemoDAO memoDAO;

	@Resource(name = "todoDAO")
	private TodoDAO todoDAO;

	@Override
	public void addMemo(Memo memo, MemoShare share) {
		memoDAO.addMemo(memo);
		share.setMemo(memo);
		memoDAO.addShare(memo, share);
	}

	@Override
	public void updateMemo(Memo memo) {
		memoDAO.updateMemo(memo);
	}

	@Override
	public void delMemo(Long memoId) {
		memoDAO.delMemo(memoId);
	}

	@Override
	public Memo readMemo(Long memoId) {
		return memoDAO.readMemo(memoId);
	}

	@Override
	public List<Memo> getAllMemo(Long userId) {
		return memoDAO.getAllMemo(userId);
	}

	@Override
	public List<Memo> getCtgrMemo(Long userId, Long ctgrId) {
		return memoDAO.getCtgrMemo(userId, ctgrId);
	}

	@Override
	public List<Memo> getPtlList(Long userId) {
		return memoDAO.getPtlList(userId);
	}

	@Override
	public void addTodo(MemoTodo todo) {
		todoDAO.addTodo(todo);
	}

	@Override
	public void checkTodo(Long todoId, boolean checked) {
		todoDAO.checkTodo(todoId, checked);
	}

	@Override
	public void allCheckTodo(Long memoId, boolean checked) {
		memoDAO.updateTodo(memoId, checked);
	}

	@Override
	public void delTodo(Long todoId) {
		todoDAO.delTodo(todoId);
	}
}
