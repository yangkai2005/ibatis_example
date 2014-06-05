package org.facejava.dao;

import java.sql.SQLException;
import java.util.List;

import org.facejava.factory.IbatisSqlMapperFactory;
import org.facejava.vo.User;

import com.ibatis.sqlmap.client.SqlMapClient;
/**
 * 通过测试可以看出，对于应用层而言，程序员面对的是传统意义上的数据对象，
 * 而非JDBC中烦杂的ResultSet，这使得上层逻辑开发人员的工作量大大减轻，同时代码更加清晰简洁。
 * 数据库操作在映射文件中加以定义，从而将数据存储逻辑从上层逻辑代码中独立出来。
 * 而底层数据操作的SQL 可配置化，使得我们可以控制最终的数据操作方式，
 * 通过SQL的优化获得最佳的数据库执行效能，这在依赖SQL自动生成的"全自动"ORM机制中是所难以实现的。
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
	 * 基于JDBC 的事务管理机制
	 * ibatis 提供了自动化的JDBC 事务管理机制。
	 * 对于传统JDBC Connection 而言，我们获取Connection实例之后,
	 * 需要调用Connection.setAutoCommit 设定事务提交模式。
	 * 在AutoCommit 为true 的情况下，JDBC会对我们的操作进行自动提交，此时每个JDBC操作都是一个独立的任务。
	 * 为了实现整体事务的原子性，我们需要将AutoCommit设为false,
	 * 并结合Connection.commit/rollback方法进行事务的提交/回滚操作。
	 * ibatis 的所谓"自动化的事务提交机制",即ibatis会根据当前的调用环境,自动判断操作是否需要自动提交。
	 * 如果代码没有显式的调用SqlMapClient.startTransaction()方法,
	 * 则ibatis会将当前的数据库操作视为自动提交模式(AutoCommit=true),
	 * 在执行的时候，会自动判定运行环境，这里操作sqlMap.insert, 
	 * ibatis当前的insert并没有相对应的事务范围(startTransaction 和endTransaction 代码块)，
	 * 于是ibatis 将其作为一个单独的事务，并自动提交。
	 * 对于下面的代码，insert 执行了两次，与其相对应，事务也提交了两次（即每个insert 操作为一个单独的事务）。
	 * 不过，值得注意的是，这里的所谓"自动判定"，可能有些误导，ibatis 并没有去检查当前是否已经有事务开启，
	 * 从而判断当前数据库连接是否设定为自动提交。
	 * 实际上，在执行insert 语句时，sqlMap 会检查当前的Session 是否已经关联了某个数据库连接，
	 * 如果没有，则取一个数据库连接，将其AutoCommit 属性设为true ，然后执行insert 操作，执行完之后又将这个连接释放。
	 * 这样，上面两次insert 操作实际上先后获取了两个数据库连接，
	 * 而不是我们通常所认为的两次insert 操作都基于同一个JDBC Connection 。这点在开发时需特别注意。
	 * 
	 * 所以,对于多条SQL 组合而成的一个JDBC 事务操作而言，
	 * 必须使用startTransaction、commit 和endTransaction 操作以实现整体事务的原子性。
	 * 如果u1 或者u2 的insert 操作失败，整个事务就会在endTransaction 时回滚，从而保证了两次update 操作的原子性。
	 * */
	public void transaction() throws SQLException {
		try {
			sqlMapper.startTransaction();
			User u1 = new User("Tom","男");
			sqlMapper.insert("insertUser", u1);
			System.out.println("插入第一条数据成功 >>>>>>>>> ");
//			int i = 5/0;
			User u2 = new User("Wen","男");
			sqlMapper.insert("insertUser", u2);
			System.out.println("插入第二条数据成功 >>>>>>>>> ");
			sqlMapper.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			sqlMapper.endTransaction();
		}
		
	}
	

}
