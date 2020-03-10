package com.makao.memo.service;

import java.util.List;

import com.makao.memo.entity.Memo;

public interface MemoService
{
	public void addMemo(Memo memo);
	
	public void updateMemo(Memo memo);
	
	public void delMemo(Long memoId);
	
	public Memo readMemo(Long memoId);
	
	public List<Memo> getAllMemo(Long userId);
	
	public List<Memo> getCtgrMemo(Long userId, Long ctgrId);
}
