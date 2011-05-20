package nutz.daostudy;

import nutz.test.pojo.Pet;

import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

public class TransactionRun extends Run {

	public static void main(String [] args) throws Exception {
		testTrans();
	}
	
	public static void testTrans() throws Exception {
		final Pet pet1 = dao.fetch(Pet.class, "xiaobai");
		final Pet pet2 = dao.fetch(Pet.class, "xiaohei");
		
		pet1.setName("baibai");
		pet2.setName("heihei");
		
		// begin transaction
		Trans.exec(new Atom() {
			public void run() {
				dao.update(pet1);
				dao.update(pet2);
			}
		});
		// end transaction
	}
	
	/**
	 * 嵌套事务
	 * 
	 * 如果指定隔离级别，则最外层的事务隔离级别为整个事务的级别
	 */
	public static void testMultiTrans() throws Exception {
		final Pet pet1 = dao.fetch(Pet.class, "xiaobai");
		final Pet pet2 = dao.fetch(Pet.class, "xiaohei");
		final Pet pet3 = dao.fetch(Pet.class, "aaa");
		
		pet1.setName("baibai");
		pet2.setName("heihei");
		pet3.setName("heige");
		
		Trans.exec(new Atom() {
			public void run() {
				dao.update(pet1);
				dao.update(pet2);

				Trans.exec(new Atom() {
					public void run() {
						dao.update(pet3);
					}
				});
			}
		});
	}
	
}
