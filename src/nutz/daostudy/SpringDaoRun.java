package nutz.daostudy;

public class SpringDaoRun extends Run {

	/**
	 import org.springframework.jdbc.datasource.DataSourceUtils;

		public class SpringDaoRunner implements DaoRunner {
		
			public void run(DataSource dataSource, ConnCallback callback) {
				Connection con = DataSourceUtils.getConnection(dataSource);  
				try {  
					callback.invoke(con);  
				}
				catch (Exception e) {  
					if (e instanceof RuntimeException)  
						throw (RuntimeException) e;  
					else  
						throw new RuntimeException(e);  
				} finally {  
					DataSourceUtils.releaseConnection(con, dataSource);
				}  
			}
		}
		任何时候你可以通过:
		
		dao.setRunner(new SpringDaoRunner());

	 */
	
}
