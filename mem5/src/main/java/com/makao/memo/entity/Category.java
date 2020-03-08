package com.makao.memo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable
{

	private static final long serialVersionUID = -6053047924394174422L;

	@Id
	@Column(name = "ctgrid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ctgr_name")
	private String ctgrName;

	@Column(name = "parentid")
	private Long parentId;

	@Column(name = "ctgr1")
	private Long ctgr1;

	@Column(name = "ctgr2")
	private Long ctgr2;

	@Column(name = "ctgr3")
	private Long ctgr3;

	@Column(name = "userid")
	private Long userId;

	@Column(name = "idx")
	private int idx;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCtgrName()
	{
		return ctgrName;
	}

	public void setCtgrName(String ctgrName)
	{
		this.ctgrName = ctgrName;
	}

	public Long getParentId()
	{
		return parentId;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	public Long getCtgr1()
	{
		return ctgr1;
	}

	public void setCtgr1(Long ctgr1)
	{
		this.ctgr1 = ctgr1;
	}

	public Long getCtgr2()
	{
		return ctgr2;
	}

	public void setCtgr2(Long ctgr2)
	{
		this.ctgr2 = ctgr2;
	}

	public Long getCtgr3()
	{
		return ctgr3;
	}

	public void setCtgr3(Long ctgr3)
	{
		this.ctgr3 = ctgr3;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public int getIdx()
	{
		return idx;
	}

	public void setIdx(int idx)
	{
		this.idx = idx;
	}

}
