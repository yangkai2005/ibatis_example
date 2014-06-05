package org.facejava.test;

import static org.junit.Assert.*;

import java.util.List;

import org.facejava.dao.UserDao;
import org.facejava.dao.UserDaoImpl;
import org.facejava.vo.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUserDao {
	
	private static UserDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new UserDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddUser() {
		try {
			User user = new User("张学友","男");
			dao.addUser(user);
			System.out.println("添加用户完成 >>>>>>>>> ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteUserById() {
		try {
			boolean flag = dao.removeUserById(9);
			Assert.assertTrue(flag);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveAllUser() {
		try {
			boolean flag = dao.removeAllUser();
			Assert.assertTrue(flag);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testGetUserById() {
		try {
			User user = dao.getUserById(1);
			System.out.println(user);
			Assert.assertNotNull(user);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllUser() {
		try {
			List<User> list = dao.getAllUser();
			System.out.println(list);
			Assert.assertNotNull(list);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testTransaction() {
		try {
			dao.transaction();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
