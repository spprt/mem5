package com.makao.memo.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.makao.memo.entity.User;
import com.makao.memo.exception.AlreadyExistingEmailException;
import com.makao.memo.persistance.UserDAO;
import com.makao.memo.util.AuthInfo;
import com.makao.memo.util.LoginCommand;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Override
	@Transactional
	public void addUser(User user) {
		System.out.println(user);
		User exist = getUser(user.getEmail());
		if (null != exist) {
			throw new AlreadyExistingEmailException();
		}
		userDAO.addUser(user);
	}

	@Override
	@Transactional
	public List<User> getAllUser() {
		return userDAO.getAllUser();
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		userDAO.deleteUser(id);
	}

	public User getUser(Long id) {
		return userDAO.getUser(id);
	}

	public User getUser(String email) {
		return userDAO.getUser(email);
	}

	public User updateUser(User user) {
		return userDAO.updateUser(user);
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public AuthInfo loginAuth(LoginCommand loginCommand) {
		User user = getUser(loginCommand.getEmail());
		if (user == null) {
//			throw new IdPasswordNotMatchingException();
			user = new User(loginCommand.getEmail(), loginCommand.getName(), loginCommand.getOauthId(),
					loginCommand.getImageUrl());
			addUser(user);
		} else {
//			updateUser(user);
		}

		return new AuthInfo(user.getId(), user.getEmail(), user.getName());
	}

}
