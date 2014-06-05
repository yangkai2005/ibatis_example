package org.facejava.dao;

import java.sql.SQLException;
import java.util.List;

import org.facejava.factory.IbatisSqlMapperFactory;
import org.facejava.vo.User;

import com.ibatis.sqlmap.client.SqlMapClient;
/**
 * ͨ�����Կ��Կ���������Ӧ�ò���ԣ�����Ա��Ե��Ǵ�ͳ�����ϵ����ݶ���
 * ����JDBC�з��ӵ�ResultSet����ʹ���ϲ��߼�������Ա�Ĺ����������ᣬͬʱ�������������ࡣ
 * ���ݿ������ӳ���ļ��м��Զ��壬�Ӷ������ݴ洢�߼����ϲ��߼������ж���������
 * ���ײ����ݲ�����SQL �����û���ʹ�����ǿ��Կ������յ����ݲ�����ʽ��
 * ͨ��SQL���Ż������ѵ����ݿ�ִ��Ч�ܣ���������SQL�Զ����ɵ�"ȫ�Զ�"ORM��������������ʵ�ֵġ�
 * */
public class UserDaoImpl implements UserDao {
	
	private SqlMapClient sqlMapper;

	public UserDaoImpl() {
		super();
		sqlMapper = IbatisSqlMapperFactory.getSqlMapClient();
	}

	public void addUser(User user) throws SQLException {
		sqlMapper.insert("insertUser", user);
	}

	public boolean removeAllUser() throws SQLException {
		int result = sqlMapper.delete("deleteAllUser");
		return result > 0 ? true : false;
	}

	public boolean removeUserById(Integer id) throws SQLException {
		int result = sqlMapper.delete("deleteUserById",id);
		return result > 0 ? true : false;
	}

	public User getUserById(Integer id) throws SQLException {
		
		return (User)sqlMapper.queryForObject("getUserById", id);
	}

	public List<User> getAllUser() throws SQLException{
		List<User> list = sqlMapper.queryForList("getAllUser");
		return list;
	}
	
	/**
	 * ����JDBC ������������
	 * ibatis �ṩ���Զ�����JDBC ���������ơ�
	 * ���ڴ�ͳJDBC Connection ���ԣ����ǻ�ȡConnectionʵ��֮��,
	 * ��Ҫ����Connection.setAutoCommit �趨�����ύģʽ��
	 * ��AutoCommit Ϊtrue ������£�JDBC������ǵĲ��������Զ��ύ����ʱÿ��JDBC��������һ������������
	 * Ϊ��ʵ�����������ԭ���ԣ�������Ҫ��AutoCommit��Ϊfalse,
	 * �����Connection.commit/rollback��������������ύ/�ع�������
	 * ibatis ����ν"�Զ����������ύ����",��ibatis����ݵ�ǰ�ĵ��û���,�Զ��жϲ����Ƿ���Ҫ�Զ��ύ��
	 * �������û����ʽ�ĵ���SqlMapClient.startTransaction()����,
	 * ��ibatis�Ὣ��ǰ�����ݿ������Ϊ�Զ��ύģʽ(AutoCommit=true),
	 * ��ִ�е�ʱ�򣬻��Զ��ж����л������������sqlMap.insert, 
	 * ibatis��ǰ��insert��û�����Ӧ������Χ(startTransaction ��endTransaction �����)��
	 * ����ibatis ������Ϊһ�����������񣬲��Զ��ύ��
	 * ��������Ĵ��룬insert ִ�������Σ��������Ӧ������Ҳ�ύ�����Σ���ÿ��insert ����Ϊһ�����������񣩡�
	 * ������ֵ��ע����ǣ��������ν"�Զ��ж�"��������Щ�󵼣�ibatis ��û��ȥ��鵱ǰ�Ƿ��Ѿ�����������
	 * �Ӷ��жϵ�ǰ���ݿ������Ƿ��趨Ϊ�Զ��ύ��
	 * ʵ���ϣ���ִ��insert ���ʱ��sqlMap ���鵱ǰ��Session �Ƿ��Ѿ�������ĳ�����ݿ����ӣ�
	 * ���û�У���ȡһ�����ݿ����ӣ�����AutoCommit ������Ϊtrue ��Ȼ��ִ��insert ������ִ����֮���ֽ���������ͷš�
	 * ��������������insert ����ʵ�����Ⱥ��ȡ���������ݿ����ӣ�
	 * ����������ͨ������Ϊ������insert ����������ͬһ��JDBC Connection ������ڿ���ʱ���ر�ע�⡣
	 * 
	 * ����,���ڶ���SQL ��϶��ɵ�һ��JDBC ����������ԣ�
	 * ����ʹ��startTransaction��commit ��endTransaction ������ʵ�����������ԭ���ԡ�
	 * ���u1 ����u2 ��insert ����ʧ�ܣ���������ͻ���endTransaction ʱ�ع����Ӷ���֤������update ������ԭ���ԡ�
	 * */
	public void transaction() throws SQLException {
		try {
			sqlMapper.startTransaction();
			User u1 = new User("Tom","��");
			sqlMapper.insert("insertUser", u1);
			System.out.println("�����һ�����ݳɹ� >>>>>>>>> ");
//			int i = 5/0;
			User u2 = new User("Wen","��");
			sqlMapper.insert("insertUser", u2);
			System.out.println("����ڶ������ݳɹ� >>>>>>>>> ");
			sqlMapper.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			sqlMapper.endTransaction();
		}
		
	}
	

}
