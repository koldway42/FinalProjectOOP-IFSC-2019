package project.restaurant.controller;

import java.util.List;

import project.restaurant.dao.UserDao;
import project.restaurant.model.User;

public class UserLogController {
	public static final String LOG_FILE = "user_list.pdf";
	public static final String LOG_TITLE = "User List";
	public static final String[] LOG_HEADER = { "ID", "Name", "Created At", "Email" };

	private UserDao userDao;

	public UserLogController() {
		this.setUserDao(new UserDao());
	}

	public List<User> LogData() {
		return this.getUserDao().getAllLog();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
}
