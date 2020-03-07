package com.makao.memo.persistance;

import java.util.List;

import com.makao.memo.entity.User;

public interface UserDAO {

	public void addUser(User user);

	public List<User> getAllUser();

	public void deleteUser(Long id);

	public User updateUser(User user);

	public User getUser(Long id);

	public User getUser(String email);
}
