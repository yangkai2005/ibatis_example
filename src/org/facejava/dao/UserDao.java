package org.facejava.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.facejava.vo.User;

public interface UserDao {
	
	void addUser(User user) throws SQLException;
	
	boolean removeUserById(Integer id) throws SQLException;
	
	boolean removeAllUser() throws SQLException;
	
	User getUserById(Integer id) throws SQLException;
	
	List<User> getAllUser()throws SQLException;

	void transaction() throws SQLException;
}
