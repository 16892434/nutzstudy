package nutz.daostudy;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

import nutz.test.pojo.Food;
import nutz.test.pojo.Pet;

public class ManyToManyRun extends Run {

	public static void main(String [] args) throws Exception {
		// testInsertWith();
		testFetchLinks();
	}
	
	/**
	 * ͬʱ����food��pets
	 */
	public static void testInsertWith() throws Exception {
		Food food = new Food();
		
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(new Pet("è"));
		pets.add(new Pet("��"));
		
		food.setName("��");
		food.setPets(pets);
		
		out.println((dao.insertWith(food, "pets")).getId());
	}
	
	/**
	 * ��ȡ��������
	 */
	public static void testFetchLinks() throws Exception {
		Food food = dao.fetch(Food.class, "��");
		
		dao.fetchLinks(food, "pets");
		
		out.println(food.getPets().size());
	}
}
