package com.makao.memo.persistance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.makao.memo.entity.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addCategory(Category ctgr) {
		getSession().save(ctgr);
	}

	@Override
	public void delCatagory(Long id) {
		Category ctgr = (Category) getSession().get(Category.class, id);
		getSession().delete(ctgr);

	}

	@Override
	public Category getCategory(Long id) {
		return (Category) getSession().get(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategory(Long userId) {
		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.addOrder(Order.asc("idx"));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return null;
	}

	@Override
	public void updateCategory(Category ctgr) {
		getSession().update(ctgr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getChildrenCategory(Long parentId) {
		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("parentId", parentId));
			criteria.addOrder(Order.asc("idx"));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getRootCategory(Long userId) {
		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.isNull("parentId"));
			criteria.addOrder(Order.asc("idx"));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return null;
	}

	@Override
	public int getMaxCount(Long parentId, Long userId) {
		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("userId", userId));
			if (null == parentId) {
				criteria.add(Restrictions.isNull("parentId"));
			} else {
				criteria.add(Restrictions.eq("parentId", parentId));
			}
			criteria.setProjection(Projections.rowCount());
			return ((Long) criteria.uniqueResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return 0;
	}

	@Override
	public void changeIdx(Long ctgrId, int idx) {
		Category ctgr = getCategory(ctgrId);
		int oldIdx = ctgr.getIdx();

		String jpql = "UPDATE Category SET idx = :idx WHERE id = :ctgrId";
		Query query = getSession().createQuery(jpql);
		query.setParameter("idx", idx);
		query.setParameter("ctgrId", ctgrId);
		query.executeUpdate();

		if (oldIdx > idx) {
			jpql = "UPDATE Category SET idx = idx + 1 WHERE id <> :ctgrId AND idx >= :idx AND idx < :oldIdx";
			query = getSession().createQuery(jpql);
			query.setParameter("ctgrId", ctgrId);
			query.setParameter("idx", idx);
			query.setParameter("oldIdx", oldIdx);
			query.executeUpdate();
		}

		if (oldIdx < idx) {
			jpql = "UPDATE Category SET idx = idx - 1 WHERE id <> :ctgrId AND idx <= :idx AND idx > :oldIdx";
			query = getSession().createQuery(jpql);
			query.setParameter("ctgrId", ctgrId);
			query.setParameter("idx", idx);
			query.setParameter("oldIdx", oldIdx);
			query.executeUpdate();
		}

	}

}
