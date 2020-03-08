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

}
