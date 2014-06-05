package org.facejava.factory;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IbatisSqlMapperFactory {
	//SqlMapClient是ibatis运作的核心，所有操作均通过SqlMapClient实例完成。
	private static SqlMapClient sqlMapper;
	
	static {
	    try {
	      Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
	      sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
	      reader.close(); 
	    } catch (IOException e) {
	      // Fail fast.
	      throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
	    }
	  }
	
	 public static SqlMapClient getSqlMapClient(){
		 try {
			return sqlMapper;
		} catch (Exception e) {
			System.out.println("%%%%%%%%%%%%%%get SqlMapClient error%%%%%%%%%%%%%%%%%");
			return null;
		}
	 }

}
