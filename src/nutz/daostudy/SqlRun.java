package nutz.daostudy;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import nutz.test.pojo.Master;
import nutz.test.pojo.Pet;
import nutz.util.DBUtil;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

public class SqlRun extends Run {

	public static void main(String [] args) throws Exception {
		// testSqlInsert();
		// testSqlCallBack();
		// testSqlSelect();
		// testFromSqlFile();
		testEntityFetch();
		testEntityQuery();
	}
	
	/**
	 * 动态SQL处理insert、delete、update时类似，在处理select时取返回结果集需要用回调
	 * @throws Exception
	 */
	public static void testSqlInsert() throws Exception {
		Sql sql = Sqls.create("INSERT INTO $table_name($col_name) value(@col_value)");
		sql.vars().set("table_name", "t_master").set("col_name", "name");
		sql.params().set("col_value", "强哥");
		dao.execute(sql);
	}
	
	/**
	 * 用回调获取返回结果集 
	 */
	public static void testSqlCallBack() throws Exception {
		Sql sql = Sqls.create("SELECT name FROM t_pet WHERE name LIKE @name");
		sql.params().set("name", "小%");
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<String> list = new LinkedList<String>();
				while(rs.next()) {
					list.add(rs.getString("name"));
				}
				return list;
			}
		});
		
		dao.execute(sql);
		
		for(String str : sql.getList(String.class)) {
			out.println(str);
		}
	}
	
	/**
	 * 直接使用默认的callback entity 
	 */
	public static void testSqlSelect() throws Exception {
		Sql sql = Sqls.create("SELECT m.* FROM t_master m JOIN t_pet p ON p.masterId = m.id WHERE p.name = '小白'");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(Master.class));
		dao.execute(sql);
		List<Master> list = sql.getList(Master.class);
		
		for(Master master : list) {
			out.println(master.getName());
		}
	}
	
	/**
	 * Sql 文件 
	 */
	public static void testFromSqlFile() throws Exception {
		Dao dao = new NutDao(DBUtil.makeDataSource(), new FileSqlManager("sqls.txt"));
		out.println(dao.sqls().count());
	}
	
	/**
	 * 利用dao同时执行多条SQL 
	 */
	public static void testExecMultiSql() throws Exception {
		Sql uMaster = dao.sqls().create("master.update");
		Sql uPet = dao.sqls().create("pet.update");
		
		uMaster.params().set("petId", 20).set("masterId", 16);
		uPet.params().set("petId", 20).set("masterId", 16);
		
		dao.execute(uMaster, uPet);
	}
	
	/**
	 * 利用comboSql同时执行多条语句 
	 */
	public static void testComboSql() throws Exception {
		Sql sql = dao.sqls().createCombo("master.update", "pet.update");
		sql.params().set("petId", 20).set("masterId", 16);
		dao.execute(sql);
	}
	
	/**
	 * Sql中使用Condition占位符组织复杂SQL 
	 */
	public static void testSqlCondition() throws Exception {
		Sql sql = Sqls.create("SELECT name FROM t_pet $condition");
		sql.setCondition(Cnd.where("id", ">", 15)).setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<String> list = new LinkedList<String>();
				while(rs.next()) {
					list.add(rs.getString("name"));
				}
				return list;
			}
		});
		
		dao.execute(sql);
		
		for(String name : sql.getList(String.class)) {
			out.println(name);
		}
	}
	
	/**
	 * 使用Entity<?>与内置的回调
	 */
	
	/**
	 * 通过自定义Condition
	 */
	public static void testEntity() throws Exception {
		Sql sql = Sqls.create("UPDATE t_pet SET masterId = @masterId $condition");
		Entity<Pet> entity = dao.getEntity(Pet.class);
		sql.setEntity(entity).setCondition(new Condition() {
			public String toSql(Entity<?> entity) {
				return String.format("%s LIKE 'Y%'", entity.getField("name").getColumnName());
			}
		});
		
		dao.execute(sql);
	}
	
	/**
	 * 指定callback
	 * 
	 * 这种可以指定默认的callback有两种，所以最后可以通过：
	 * 	sql.getObject(Class<?> clazz);
	 * 	sql.getList(Class<?> clazz);
	 * 来取结果集。
	 * 
	 * fetch	取一个对象
	 * query	取一组对象（集合）
	 * 
	 */
	public static void testDefaultEntity() throws Exception {
		Sql sql = Sqls.create("SELECT * FROM t_pet $condition");
		sql.setCallback(Sqls.callback.entities());
		Entity<Pet> entity = dao.getEntity(Pet.class);
		sql.setEntity(entity).setCondition(Cnd.wrap("id=15"));
		dao.execute(sql);
		// out.println(sql.getList(Pet.class));
		out.println(sql.getObject(Pet.class));
	}

	/**
	 * 使用内置callback来Fetch一个对象
	 */
	public static void testEntityFetch() throws Exception {
		out.println("\n" + StringUtils.center("testEntityFetch", 80, "*"));
		Sql sql = Sqls.fetchEntity("SELECT * FROM t_pet $condition");
		Entity<Pet> entity = dao.getEntity(Pet.class);
		sql.setEntity(entity).setCondition(Cnd.wrap("id=1"));
		dao.execute(sql);
		out.println(sql.getObject(Pet.class).getName());
	}
	
	/**
	 * 使用内置callback来Query一组对象
	 */
	public static void testEntityQuery() throws Exception {
		out.println("\n" + StringUtils.center("testEntityQuery", 80, "*"));
		Sql sql = Sqls.queryEntity("SELECT * FROM t_pet $condition");
		Entity<Pet> entity = dao.getEntity(Pet.class);
		sql.setEntity(entity).setCondition(Cnd.wrap("id>1"));
		dao.execute(sql);
		
		List<Pet> list = sql.getList(Pet.class);
		
		for(Pet pet : list) {
			out.println(pet.getName());
		}
	}
}
