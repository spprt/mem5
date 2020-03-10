package com.makao.memo.persistance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.makao.memo.entity.Memo;

@Repository("memoDAO")
public class MemoDAOImpl implements MemoDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addMemo(Memo memo)
	{
		getSession().save(memo);
	}

	@Override
	public void updateMemo(Memo memo)
	{
		getSession().update(memo);
	}

	@Override
	public void delMemo(Long memoId)
	{
		Memo memo = (Memo) getSession().get(Memo.class, memoId);
		getSession().delete(memo);
	}

	@Override
	public Memo readMemo(Long memoId)
	{
		return (Memo) getSession().get(Memo.class, memoId);
	}

	@Override
	public List<Memo> getAllMemo(Long userId)
	{
		Session session = this.sessionFactory.openSession();
		try
		{
			Criteria criteria = session.createCriteria(Memo.class);
			criteria.add(Restrictions.eq("regUserId", userId));
			criteria.addOrder(Order.desc("modDate"));
			criteria.addOrder(Order.desc("id"));
			return criteria.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (session != null)
				session.close();
		}

		return null;
	}

	@Override
	public List<Memo> getCtgrMemo(Long userId, Long ctgrId)
	{
		Session session = this.sessionFactory.openSession();
		try
		{
			Criteria criteria = session.createCriteria(Memo.class);
			criteria.add(Restrictions.eq("regUserId", userId));
			//흠.......분류 조인해야함...
			criteria.addOrder(Order.desc("modDate"));
			criteria.addOrder(Order.desc("id"));
			return criteria.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (session != null)
				session.close();
		}

		return null;
	}

}
