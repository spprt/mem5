package com.makao.memo.service;

import java.util.List;

import com.makao.memo.entity.User;
import com.makao.memo.util.AuthInfo;
import com.makao.memo.util.LoginCommand;

public interface UserService {
	public void addUser(User user);

	public List<User> getAllUser();

	public void deleteUser(Long id);

	public User getUser(Long id);

	public User getUser(String email);

	public User updateUser(User user);

	AuthInfo loginAuth(LoginCommand loginCommand);
}
