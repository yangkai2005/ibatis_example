package org.facejava.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.facejava.dao.StudentDao;
import org.facejava.dao.StudentDaoImpl;
import org.facejava.vo.Clazz;
import org.facejava.vo.Student;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestStudent {
	
	private static StudentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new StudentDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetStudentById() {
		try {
			Student stu = dao.findStudentById(1);
			System.out.println(stu.getName());
			System.out.println(stu.getClazz().getName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("%%%%%%%%getStudet fail%%%%%%%%%%%");
		}
		
	}
	
	@Test
	public void testGetAllStudent() {
		try {
		List<Student> list = dao.findAllStudent();
		Student s = list.get(1);
		System.out.println(s.getName());
		System.out.println(s.getClazz().getName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("%%%%%%%%getStudet fail%%%%%%%%%%%");
		}
		
	}
	
	@Test
	public void testGetClazzById() {
		try {
			Clazz c = dao.findClazzById(1);
			System.out.println(c.getName());
			System.out.println(c.getStudents());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("%%%%%%%%getStudet fail%%%%%%%%%%%");
		}
		
	}

	
}
