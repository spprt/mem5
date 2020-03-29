package com.makao.memo.persistance;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.makao.memo.entity.Memo;
import com.makao.memo.entity.MemoShare;

@Repository("memoDAO")
public class MemoDAOImpl implements MemoDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addMemo(Memo memo) {
		getSession().save(memo);
	}

	@Override
	public void updateMemo(Memo memo) {
		memo.setModDate(new Date());
		getSession().update(memo);
	}

	@Override
	public void delMemo(Long memoId) {
		Memo memo = readMemo(memoId);
		memo.setDel(true);
		memo.setModDate(new Date());
		getSession().saveOrUpdate(memo);
	}
	
	@Override
	public void restoreMemo(Long memoId) {
		Memo memo = readMemo(memoId);
		memo.setDel(false);
		memo.setModDate(new Date());
		getSession().saveOrUpdate(memo);
	}

	@Override
	public void removeMemo(Long memoId) {
		Memo memo = readMemo(memoId);
		getSession().delete(memo);
	}

	@Override
	public Memo readMemo(Long memoId) {
		return (Memo) getSession().get(Memo.class, memoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memo> getAllMemo(Long userId) {
		String jpql = "SELECT m.id, m.title, m.type, m.regDate, m.modDate FROM Memo m where regUserId = :userId AND m.del = :del order by modDate desc, id desc";
		Query query = getSession().createQuery(jpql);
		query.setParameter("userId", userId);
		query.setParameter("del", false);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memo> getCtgrMemo(Long userId, Long ctgrId) {
		String jpql = "SELECT m.id, m.title, m.type, m.regDate, m.modDate FROM Memo m, MemoShare s where s.userId = :userId AND s.ctgrId = :ctgrId AND s.memo.id = m.id AND m.del = :del";
		Query query = getSession().createQuery(jpql);
		query.setParameter("userId", userId);
		query.setParameter("ctgrId", ctgrId);
		query.setParameter("del", false);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memo> getPtlList(Long userId) {
		String jpql = "SELECT m FROM Memo m, MemoShare s where s.userId = :userId AND s.memo.id = m.id AND m.del = :del";
		Query query = getSession().createQuery(jpql);
		query.setParameter("userId", userId);
		query.setParameter("del", false);
		query.setFirstResult(0);
		query.setMaxResults(12);
		return query.list();
	}

	@Override
	public void updateTodo(Long memoId, boolean checked) {
		String jpql = "UPDATE MemoTodo SET complete = :checked where memo.id = :memoId";
		Query query = getSession().createQuery(jpql);
		query.setParameter("checked", checked);
		query.setParameter("memoId", memoId);
		query.executeUpdate();
	}

	@Override
	public void addShare(Memo memo, MemoShare share) {
		getSession().save(share);
	}

	@Override
	public void updateShare(MemoShare share) {
		getSession().saveOrUpdate(share);
	}

	@Override
	public void sortTodo(Long id, int index, Long memoId) {
		String jpql = "UPDATE MemoTodo SET idx = :index where id = :id AND memo.id = :memoId";
		Query query = getSession().createQuery(jpql);
		query.setParameter("index", index);
		query.setParameter("id", id);
		query.setParameter("memoId", memoId);
		query.executeUpdate();
	}
}
