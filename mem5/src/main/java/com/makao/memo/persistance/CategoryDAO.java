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
}
