package org.facejava.dao;

import java.sql.SQLException;
import java.util.List;

import org.facejava.factory.IbatisSqlMapperFactory;
import org.facejava.vo.Clazz;
import org.facejava.vo.Student;

import com.ibatis.sqlmap.client.SqlMapClient;

public class StudentDaoImpl implements StudentDao {
	
	private  SqlMapClient sqlMapper;

	public StudentDaoImpl() {
		super();
		sqlMapper = IbatisSqlMapperFactory.getSqlMapClient();
	}

	public Student findStudentById(Integer id)throws SQLException {
		Student stu = (Student)sqlMapper.queryForObject("getStudentById",id);
		return stu;
	}

	public List<Student> findAllStudent()  throws SQLException{
		List<Student> list = sqlMapper.queryForList("getAllStudents");
		return list;
	}

	public Clazz findClazzById(Integer id) throws SQLException {
		
		return (Clazz)sqlMapper.queryForObject("getClazzById",id);
	}

	

}
