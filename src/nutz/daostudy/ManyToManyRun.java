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
	 * 同时插入food和pets
	 */
	public static void testInsertWith() throws Exception {
		Food food = new Food();
		
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(new Pet("猫"));
		pets.add(new Pet("狗"));
		
		food.setName("鱼");
		food.setPets(pets);
		
		out.println((dao.insertWith(food, "pets")).getId());
	}
	
	/**
	 * 获取关联对象
	 */
	public static void testFetchLinks() throws Exception {
		Food food = dao.fetch(Food.class, "鱼");
		
		dao.fetchLinks(food, "pets");
		
		out.println(food.getPets().size());
	}
}
