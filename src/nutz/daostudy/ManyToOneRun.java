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
	 * ͬʱ����master��pets
	 */
	public static void testInsertWith() throws Exception {
		Master master = new Master();
		master.setName("�ڸ�");
		
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(new Pet("С��"));
		pets.add(new Pet("СС��"));
		
		master.setPets(pets);
		
		out.println((dao.insertWith(master, "pets")).getId());
	}
	
	/**
	 * ��������pets����ʱpet��masterId���ò���ȷ 
	 */
	public static void testInsertLinks() throws Exception {
		Master master = new Master();
		master.setName("ǿ��");
		
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(new Pet("Сǿ"));
		pets.add(new Pet("ССǿ"));
		
		master.setPets(pets);

		out.println((dao.insertLinks(master, "pets")).getId());
	}
	
	/**
	 * ��ȡ��������
	 */
	public static void testFetchLinks() throws Exception {
		Master master = dao.fetch(Master.class, "�ڸ�");
		
		dao.fetchLinks(master, "pets");
		
		out.println(master.getPets().size());
	}
}
