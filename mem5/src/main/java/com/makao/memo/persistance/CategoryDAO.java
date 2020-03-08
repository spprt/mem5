package com.makao.memo.persistance;

import java.util.List;

import com.makao.memo.entity.Category;

public interface CategoryDAO
{
	public void addCategory(Category ctgr);

	public void delCatagory(Long id);
	
	public void updateCategory(Category ctgr);

	public Category getCategory(Long id);

	public List<Category> getAllCategory(Long userId);
	
	public List<Category> getChildrenCategory(Long parentId);
	
	public List<Category> getRootCategory(Long userId);
	
	public int getMaxCount(Long parentId, Long userId);
}
