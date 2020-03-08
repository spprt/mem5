package com.makao.memo.persistance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.makao.memo.entity.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addCategory(Category ctgr)
	{
		getSession().save(ctgr);
	}

	@Override
	public void delCatagory(Long id)
	{
		Category ctgr = (Category) getSession().get(Category.class, id);
		getSession().delete(ctgr);

	}

	@Override
	public Category getCategory(Long id)
	{
		return (Category) getSession().get(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategory(Long userId)
	{
		Session session = this.sessionFactory.openSession();
		try
		{
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.addOrder(Order.asc("idx"));
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
	public void updateCategory(Category ctgr)
	{
		getSession().update(ctgr);
	}

}
