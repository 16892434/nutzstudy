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
		// ����JDBC��Driver��  
		ds.setDriverClass("org.gjt.mm.mysql.Driver");  // ������ Config ����������ļ���ȡ  
		// ����JDBC��URL  
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/nutz");  
		// �������ݿ�ĵ�¼�û���  
		ds.setUser("root");  
		// �������ݿ�ĵ�¼�û�����  
		ds.setPassword("mysqladmin");  
		// �������ӳص����������  
		ds.setMaxPoolSize(10);  
		// �������ӳص���С������  
		ds.setMinPoolSize(5);  
		
		Dao dao = new NutDao(ds);
		Person person = new Person();
		person.setName("����");
		person.setAge(22);
		dao.insert(person);
		
		Person p = dao.fetch(Person.class, "����");
		System.out.println(p.getId());
		
		Person pp = dao.fetch(Person.class, 2);
		System.out.println(pp.getName());
		
		p.setName("����");
		dao.update(p);
		
		// dao.delete(Person.class, "����");
		
		List<Person> list = dao.query(Person.class, null, null);
		System.out.println(list.size());
		
		ds.close();
	}
	
	     
}
