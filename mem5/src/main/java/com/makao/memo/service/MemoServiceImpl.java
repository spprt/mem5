package com.makao.memo.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.makao.memo.entity.Memo;
import com.makao.memo.persistance.MemoDAO;

@Service("memoService")
@Transactional
public class MemoServiceImpl implements MemoService
{
	@Resource(name = "memoDAO")
	private MemoDAO memoDAO;

	@Override
	public void addMemo(Memo memo)
	{
		memoDAO.addMemo(memo);
	}

	@Override
	public void updateMemo(Memo memo)
	{
		memoDAO.updateMemo(memo);
	}

	@Override
	public void delMemo(Long memoId)
	{
		memoDAO.delMemo(memoId);
	}

	@Override
	public Memo readMemo(Long memoId)
	{
		return memoDAO.readMemo(memoId);
	}

	@Override
	public List<Memo> getAllMemo(Long userId)
	{
		return memoDAO.getAllMemo(userId);
	}

	@Override
	public List<Memo> getCtgrMemo(Long userId, Long ctgrId)
	{
		return memoDAO.getCtgrMemo(userId, ctgrId);
	}

}
