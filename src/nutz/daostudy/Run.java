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
	 * ִ��insert���󻹻�ִ��select max(id) ...
	 * ָ��@Id(auto=false)��ִ��select max(id) ... 
	 */
	public static void testInsert() throws Exception {
		out.println("\n" + StringUtils.center("test insert", 80, "*"));
		Person p = new Person();
		p.setName("��5");
		p.setAge(25);
		dao.insert(p);
		out.println(p.getId());
	}
	
	/**
	 * fast insertִֻ��һ��insert��� 
	 */
	public static void testFastInsert() throws Exception {
		out.println("\n" + StringUtils.center("test fast insert", 80, "*"));
		Person p = new Person();
		p.setName("����");
		p.setAge(35);
		dao.insert(p);
		out.println(p.getId());
	}
	
	/**
	 * ͨ��@Name(casesensitive=false)���Ժ��Դ�Сд
	 * �����@PK({"id1", "id2"})�����Ӧ�ķ���Ϊfetchx, deletex����˳��Ҫ��Ӧ
	 */
	public static void testFetch() throws Exception {
		out.println("\n" + StringUtils.center("test fetch", 80, "*"));
		Person person = dao.fetch(Person.class, "����");
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
	 * Cnd.where�Ǵ����@Column("���ݿ��ֶ���")
	 * Cnd.wrap�����������ֶ���
	 * 
	 * Cnd��Ҫ�������������㽨��һ�� org.nutz.dao.Condition �ӿڵ�ʵ���� 
	 * where() ���� ��һ���������ֶ�����Ҫ�� Java ��������ֶ�����ͬ�� 
	 * where() ���� �ڶ���������ѭ SQL �ı�׼�������� >, <, >=, <= �ȵ� 
	 * �ṩ��һ�� wrap �����������ֱ��д SQL ������ 
	 * 
	 * ��� Condition c = Cnd.where("age",">",30).and("name", "LIKE", "%K%").asc("name").desc("id");
	 * ��ת��Ϊ�� WHERE age>30 AND name LIKE '%K%' ORDERBY name ASC, id DESC
	 * 
	 * ���
	 * ExpGroup e1 = Cnd.exps("name", "LIKE", "P%").and("age", ">", "20");
	 * ExpGroup e2 = Cnd.exps("name", "LIKE", "S%").and("age", "<", "30");
	 * Condition c = Cnd.where(e1).or(e2).asc("name");
	 * ��ת��Ϊ��WHERE (name LIKE 'P%' AND age>'20') OR (name LIKE 'S%' AND age<'30') ORDER BY name ASC
	 * 
	 */
	public static void testQueryByCnd() throws Exception {
		out.println("\n" + StringUtils.center("test query by cnd", 80, "*"));
		List<Person> list = dao.query(Person.class, Cnd.where("name", "like", "��%"), null);
		// List<Person> list = dao.query(Person.class, Cnd.where("age", "=", "25"), null);
		
		for(Person person : list) {
			out.println(person);
		}
		
	}
	
	/**
	 * dao.createPager ��һ�������ǵڼ�ҳ���ڶ�������һҳ�ж�������¼
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
