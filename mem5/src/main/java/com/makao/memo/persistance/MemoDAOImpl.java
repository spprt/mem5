package com.makao.memo.persistance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		getSession().update(memo);
	}

	@Override
	public void delMemo(Long memoId) {
		Memo memo = (Memo) getSession().get(Memo.class, memoId);
		getSession().delete(memo);
	}

	@Override
	public Memo readMemo(Long memoId) {
		return (Memo) getSession().get(Memo.class, memoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memo> getAllMemo(Long userId) {
		String jpql = "SELECT m.id, m.title, m.type, m.regDate, m.modDate FROM Memo m where regUserId = :userId order by modDate desc, id desc";
		Query query = getSession().createQuery(jpql);
		query.setParameter("userId", userId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memo> getCtgrMemo(Long userId, Long ctgrId) {
		String jpql = "SELECT m.id, m.title, m.type, m.regDate, m.modDate FROM Memo m, MemoShare s where s.userId = :userId AND s.ctgrId = :ctgrId AND s.memo.id = m.id";
		Query query = getSession().createQuery(jpql);
		query.setParameter("userId", userId);
		query.setParameter("ctgrId", ctgrId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Memo> getPtlList(Long userId) {
		Criteria criteria = getSession().createCriteria(Memo.class);
		criteria.setMaxResults(12);
		criteria.setFirstResult(1);
		criteria.add(Restrictions.eq("regUserId", userId));
		criteria.addOrder(Order.desc("modDate"));

		return criteria.list();
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
}
