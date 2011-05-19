package nutz.daostudy;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

import nutz.test.pojo.Master;
import nutz.test.pojo.Pet;

public class ManyToOneRun extends Run {

	public static void main(String [] args) throws Exception {
		// testInsertWith();
		// testInsertLinks();
		testFetchLinks();
	}
	
	/**
	 * 同时新增master和pets
	 */
	public static void testInsertWith() throws Exception {
		Master master = new Master();
		master.setName("黑哥");
		
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(new Pet("小黑"));
		pets.add(new Pet("小小黑"));
		
		master.setPets(pets);
		
		out.println((dao.insertWith(master, "pets")).getId());
	}
	
	/**
	 * 仅仅插入pets，此时pet的masterId设置不正确 
	 */
	public static void testInsertLinks() throws Exception {
		Master master = new Master();
		master.setName("强哥");
		
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(new Pet("小强"));
		pets.add(new Pet("小小强"));
		
		master.setPets(pets);

		out.println((dao.insertLinks(master, "pets")).getId());
	}
	
	/**
	 * 获取关联对象
	 */
	public static void testFetchLinks() throws Exception {
		Master master = dao.fetch(Master.class, "黑哥");
		
		dao.fetchLinks(master, "pets");
		
		out.println(master.getPets().size());
	}
}
