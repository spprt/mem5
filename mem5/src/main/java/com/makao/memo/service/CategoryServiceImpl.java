package com.makao.memo.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.makao.memo.entity.Category;
import com.makao.memo.persistance.CategoryDAO;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Resource(name = "categoryDAO")
	private CategoryDAO categoryDAO;

	@Override
	public void addCategory(Category ctgr) {
		if (ctgr.getId() != null) {
			categoryDAO.updateCategory(ctgr);
		} else {
			categoryDAO.addCategory(ctgr);
		}
	}

	@Override
	public void delCategory(Long id) {
		categoryDAO.delCatagory(id);
	}

	@Override
	public void updateCategory(Category ctgr) {
		categoryDAO.updateCategory(ctgr);
	}

	@Override
	public Category getCategory(Long id) {
		return categoryDAO.getCategory(id);
	}

	@Override
	public List<Category> getAllCategory(Long userId) {
		return categoryDAO.getAllCategory(userId);
	}

	@Override
	public List<Category> getChildrenCategory(Long parentId) {
		return categoryDAO.getChildrenCategory(parentId);
	}

	@Override
	public List<Category> getRootCategory(Long userId) {
		return categoryDAO.getRootCategory(userId);
	}

	@Override
	public int getMaxCount(Long parentId, Long userId) {
		return categoryDAO.getMaxCount(parentId, userId);
	}

	@Override
	public void changeIdx(Long ctgrId, int idx) {
		categoryDAO.changeIdx(ctgrId, idx);
	}

}
