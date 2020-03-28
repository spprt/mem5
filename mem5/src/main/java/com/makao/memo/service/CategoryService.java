package com.makao.memo.service;

import java.util.List;

import com.makao.memo.entity.Category;

public interface CategoryService
{
	public void addCategory(Category ctgr);
	
	public void delCategory(Long id);
	
	public void updateCategory(Category ctgr);
	
	public Category getCategory(Long id);
	
	public List<Category> getAllCategory(Long userId);

	public List<Category> getChildrenCategory(Long parentId);
	
	public List<Category> getRootCategory(Long userId);
	
	public int getMaxCount(Long parentId, Long userId);
	
	public void changeIdx(Long ctgrId, int idx);
}
