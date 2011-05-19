package nutz.daostudy;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import nutz.test.pojo.Master;
import nutz.util.DBUtil;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

public class SqlRun extends Run {

	public static void main(String [] args) throws Exception {
		// testSqlInsert();
		// testSqlCallBack();
		// testSqlSelect();
		testFromSqlFile();
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
}
