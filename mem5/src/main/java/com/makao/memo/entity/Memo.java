package com.makao.memo.entity;

import java.io.Serializable;
import java.util.Collection;
//github.com/spprt/mem5.git
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "memo")
public class Memo implements Serializable {
	private static final long serialVersionUID = -2714900674366558510L;

	@Id
	@Column(name = "memoid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "title")
	private String title;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content")
	private String content;

	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date", insertable = false)
	private Date regDate;

	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mod_date", updatable = false, insertable = false)
	private Date modDate;

	@Column(name = "isdel")
	private boolean isDel;

	@Column(name = "reg_userid")
	private Long regUserId;

	@Column(name = "mod_userid")
	private Long modUserId;

	@Column(name = "type")
	private int type;

	@OneToMany(mappedBy = "memo", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Collection<MemoTodo> todos;

	/**
	 * 메모타입: 일반노트(1)
	 */
	public static final int TYPE_NOTE = 1;
	/**
	 * 메모타입: TO-DO리스트(2)
	 */
	public static final int TYPE_TODO = 2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public boolean isDel() {
		return isDel;
	}

	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}

	public Long getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(Long regUserId) {
		this.regUserId = regUserId;
	}

	public Long getModUserId() {
		return modUserId;
	}

	public void setModUserId(Long modUserId) {
		this.modUserId = modUserId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Collection<MemoTodo> getTodos() {
		return todos;
	}

	public void setTodos(Collection<MemoTodo> todos) {
		this.todos = todos;
	}
}
