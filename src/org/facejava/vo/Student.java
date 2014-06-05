package org.facejava.vo;

import java.io.Serializable;

/**
 * CREATE TABLE tb_clazz( id INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(18) )
 * 
 * CREATE TABLE tb_student( id INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(18),
 * sex VARCHAR(18), age INT, clazz_id INT, CONSTRAINT tb_studnet_fk FOREIGN KEY
 * (CLAzz_ID) REFERENCES tb_clazz (ID) )
 * */
public class Student implements Serializable {

	private Integer id;
	private String name;
	private String sex;
	private Integer age;
//	private Integer clazz_id;

	private Clazz clazz;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String name, String sex, Integer age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", clazz=" + clazz + ", id=" + id
				+ ", name=" + name + ", sex=" + sex + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		return true;
	}

	/*public Integer getClazz_id() {
		return clazz_id;
	}

	public void setClazz_id(Integer clazzId) {
		clazz_id = clazzId;
	}
*/
	

}
