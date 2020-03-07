package com.makao.memo.util;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class LoginCommand {

	@NotEmpty(message = "이메일을 필수로 입력해 주세요.")
	private String email;

	@NotEmpty(message = "패스워드를 필수로 입력해 주세요.")
	private String password;
	private boolean rememberId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberId() {
		return rememberId;
	}

	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}

}
