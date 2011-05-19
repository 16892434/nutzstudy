package nutz.daostudy;

import static java.lang.System.out;
import nutz.test.pojo.Master;
import nutz.test.pojo.Pet;

public class OneToOneRun extends Run {

	public static void main(String [] args) throws Exception {
		// testInsertWith();
		// testInsertLinks();
		testFetch();
	}
	
	/**
	 * 同新插入关联对象
	 */
	public static void testInsertWith() throws Exception {
		Pet pet = new Pet();
		pet.setName("小白");
		Master master = new Master();
		master.setName("小新");
		pet.setMaster(master);
		
		out.println(dao.insertWith(pet, "master"));
	}

	/**
	 * 仅仅插入被关联的对象
	 */
	public static void testInsertLinks() throws Exception {
		Pet pet = new Pet();
		pet.setName("小小白");
		Master master = new Master();
		master.setName("小小新");
		pet.setMaster(master);
		
		out.println(dao.insertLinks(pet, "master"));
	}
	
	/**
	 * 仅fetch被关联的对象
	 */
	public static void testFetch() throws Exception {
		Pet pet = dao.fetch(Pet.class, "小白");
		dao.fetchLinks(pet, "master");
		out.println(pet.getMaster().getName());
	}
	
	/**
	 * 更新
	 */
	public static void testUpdate() throws Exception {
		// 同时更新pet和master
		// dao.updateWith(pet, "master");
		
		// 仅仅更新master
		// dao.updateLinks(pet, "master");
	}
	
	/**
	 * 删除
	 */
	public static void testDelete() throws Exception {
		// 同时删除pet和master
		// dao.deleteWith(pet, "master");
		
		// 仅仅删除master（每个一条SQL）
		// dao.deleteLinks(pet, "master");
		
		// 清除master（一条SQL）
		// dao.clearLinks(pet, "master");
	}
}
