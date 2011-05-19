package nutz.test;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

import nutz.test.pojo.Person;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Main {

	private static ComboPooledDataSource ds = null;  
	
	public static void main(String [] args) throws SQLException, PropertyVetoException {
		/*
		DataSource ds = DataSources.pooledDataSource(unpooledDataSource)unpooledDataSource("jdbc:mysql://localhost:3306/nutz",
			       "root",
			       "mysqladmin");
			       */
		
		ds = new ComboPooledDataSource();  
		// 设置JDBC的Driver类  
		ds.setDriverClass("org.gjt.mm.mysql.Driver");  // 参数由 Config 类根据配置文件读取  
		// 设置JDBC的URL  
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/nutz");  
		// 设置数据库的登录用户名  
		ds.setUser("root");  
		// 设置数据库的登录用户密码  
		ds.setPassword("mysqladmin");  
		// 设置连接池的最大连接数  
		ds.setMaxPoolSize(10);  
		// 设置连接池的最小连接数  
		ds.setMinPoolSize(5);  
		
		Dao dao = new NutDao(ds);
		Person person = new Person();
		person.setName("张三");
		person.setAge(22);
		dao.insert(person);
		
		Person p = dao.fetch(Person.class, "张三");
		System.out.println(p.getId());
		
		Person pp = dao.fetch(Person.class, 2);
		System.out.println(pp.getName());
		
		p.setName("李四");
		dao.update(p);
		
		// dao.delete(Person.class, "张三");
		
		List<Person> list = dao.query(Person.class, null, null);
		System.out.println(list.size());
		
		ds.close();
	}
	
	     
}
