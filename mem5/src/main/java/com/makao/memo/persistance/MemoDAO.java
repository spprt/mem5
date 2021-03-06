package com.makao.memo.persistance;

import java.util.List;

import com.makao.memo.entity.Memo;
import com.makao.memo.entity.MemoShare;

public interface MemoDAO {
	public void addMemo(Memo memo);

	public void updateMemo(Memo memo);

	public void delMemo(Long memoId);

	public void restoreMemo(Long memoId);

	public void removeMemo(Long memoId);

	public void removeAll(Long userId);

	public Memo readMemo(Long memoId);

	public List<Memo> getAllMemo(Long userId);

	public List<Memo> getCtgrMemo(Long userId, Long ctgrId);

	public List<Memo> getDelMemo(Long userId);

	public List<Memo> getPtlList(Long userId);

	public void updateTodo(Long memoId, boolean checked);

	public void addShare(Memo memo, MemoShare share);

	public void updateShare(MemoShare share);

	public void sortTodo(Long id, int index, Long memoId);

	public void checkFavorite(Long memoId, Long userId, boolean checked);
	
	public List<Memo> getFavoriteList(Long userId);
	
	public void lockMemo(Memo memo, Long userId);
	
	public void unlockMemo(Memo memo);
}
