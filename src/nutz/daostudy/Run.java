package nutz.daostudy;

import nutz.util.DBUtil;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

public abstract class Run {

	public static final Dao dao = new NutDao(DBUtil.makeDataSource());
	
}
