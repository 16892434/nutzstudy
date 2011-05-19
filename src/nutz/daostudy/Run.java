package nutz.daostudy;

import static java.lang.System.out;

import java.util.List;

import nutz.test.pojo.Person;
import nutz.util.DBUtil;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

public class Run {

	private static final Dao dao = new NutDao(DBUtil.makeDataSource());

	public static void main(String [] args) throws Exception {
		// testInsert();
		testFetch();
		testUpdate();
		testDelete();
		testQuery();
		testQueryByCnd();
		testSplitPage();
		// testClear();
		testClearByCnd();
	}
	
	/**
	 * 执行insert语句后还会执行select max(id) ...
	 * 指明@Id(auto=false)后不执行select max(id) ... 
	 */
	public static void testInsert() throws Exception {
		out.println("\n" + StringUtils.center("test insert", 80, "*"));
		Person p = new Person();
		p.setName("李5");
		p.setAge(25);
		dao.insert(p);
		out.println(p.getId());
	}
	
	/**
	 * fast insert只执行一条insert语句 
	 */
	public static void testFastInsert() throws Exception {
		out.println("\n" + StringUtils.center("test fast insert", 80, "*"));
		Person p = new Person();
		p.setName("王八");
		p.setAge(35);
		dao.insert(p);
		out.println(p.getId());
	}
	
	/**
	 * 通过@Name(casesensitive=false)可以忽略大小写
	 * 如果是@PK({"id1", "id2"})，则对应的方法为fetchx, deletex，且顺序要对应
	 */
	public static void testFetch() throws Exception {
		out.println("\n" + StringUtils.center("test fetch", 80, "*"));
		Person person = dao.fetch(Person.class, "张三");
		out.println(person.getId());
	}
	
	public static void testUpdate() throws Exception {
		out.println("\n" + StringUtils.center("test update", 80, "*"));
		Person person = dao.fetch(Person.class, 4);
		person.setName("Tom");
		dao.update(person);
		out.println(person);
	}
	
	public static void testDelete() throws Exception {
		out.println("\n" + StringUtils.center("test delete", 80, "*"));
		out.println(dao.delete(Person.class, 5));
	}
	
	public static void testQuery() throws Exception {
		out.println("\n" + StringUtils.center("test query", 80, "*"));
		List<Person> list = dao.query(Person.class, null, null);
		for(Person person : list) {
			out.println(person);
		}
	}
	
	/**
	 * Cnd.where是处理的@Column("数据库字段名")
	 * Cnd.wrap处理的是类的字段名
	 * 
	 * Cnd主要是用来快速替你建立一个 org.nutz.dao.Condition 接口的实现类 
	 * where() 函数 第一个参数是字段名，要和 Java 类里面的字段名相同。 
	 * where() 函数 第二个参数遵循 SQL 的标准，可以是 >, <, >=, <= 等等 
	 * 提供了一个 wrap 函数，你可以直接写 SQL 的条件 
	 * 
	 * 这个 Condition c = Cnd.where("age",">",30).and("name", "LIKE", "%K%").asc("name").desc("id");
	 * 会转换为： WHERE age>30 AND name LIKE '%K%' ORDERBY name ASC, id DESC
	 * 
	 * 这个
	 * ExpGroup e1 = Cnd.exps("name", "LIKE", "P%").and("age", ">", "20");
	 * ExpGroup e2 = Cnd.exps("name", "LIKE", "S%").and("age", "<", "30");
	 * Condition c = Cnd.where(e1).or(e2).asc("name");
	 * 会转换为：WHERE (name LIKE 'P%' AND age>'20') OR (name LIKE 'S%' AND age<'30') ORDER BY name ASC
	 * 
	 */
	public static void testQueryByCnd() throws Exception {
		out.println("\n" + StringUtils.center("test query by cnd", 80, "*"));
		List<Person> list = dao.query(Person.class, Cnd.where("name", "like", "李%"), null);
		// List<Person> list = dao.query(Person.class, Cnd.where("age", "=", "25"), null);
		
		for(Person person : list) {
			out.println(person);
		}
		
	}
	
	/**
	 * dao.createPager 第一个参数是第几页，第二参数是一页有多少条记录
	 */
	public static void testSplitPage() throws Exception {
		out.println("\n" + StringUtils.center("test split page", 80, "*"));
		// List<Person> people = dao.query(Person.class, null, dao.createPager(1, 4));
		List<Person> people = dao.query(Person.class, Cnd.where("age", "=", "25"), dao.createPager(1, 4));
		
		for(Person person : people) {
			out.println(person);
		}
	}
	
	public static void testClear() throws Exception {
		out.println("\n" + StringUtils.center("test clear", 80, "*"));
		out.println(dao.clear(Person.class));
	}
	
	public static void testClearByCnd() throws Exception {
		out.println("\n" + StringUtils.center("test clear by cnd", 80, "*"));
		out.println(dao.clear(Person.class, Cnd.where("age", "<", 23)));
	}
}
