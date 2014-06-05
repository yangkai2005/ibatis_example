package org.facejava.dao;

import java.sql.SQLException;
import java.util.List;

import org.facejava.vo.Clazz;
import org.facejava.vo.Student;

public interface StudentDao {
	
	Student findStudentById(Integer id) throws SQLException;

	List<Student> findAllStudent()  throws SQLException;
	
	Clazz findClazzById(Integer id) throws SQLException;
}
