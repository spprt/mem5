package com.makao.memo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "memo_share")
public class MemoShare implements Serializable {
	private static final long serialVersionUID = -1340328093530177513L;

	public MemoShare() {
	}

	public MemoShare(User user) {
		this.userId = user.getId();
		this.name = user.getName();
	}

	@Id
	@Column(name = "shareid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "memoid")
	private Memo memo;

	@Column(name = "userid")
	private Long userId;

	@Column(name = "name")
	private String name;

	@Column(name = "isreg")
	private boolean register;

	@Column(name = "isfavorite")
	private boolean favorite;

	@Column(name = "ctgrid")
	private Long ctgrId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Memo getMemo() {
		return memo;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRegister() {
		return register;
	}

	public void setRegister(boolean register) {
		this.register = register;
	}
	
	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public Long getCtgrId() {
		return ctgrId;
	}

	public void setCtgrId(Long ctgrId) {
		this.ctgrId = ctgrId;
	}

}
