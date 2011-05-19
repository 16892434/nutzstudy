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
	 * ͬ�²����������
	 */
	public static void testInsertWith() throws Exception {
		Pet pet = new Pet();
		pet.setName("С��");
		Master master = new Master();
		master.setName("С��");
		pet.setMaster(master);
		
		out.println(dao.insertWith(pet, "master"));
	}

	/**
	 * �������뱻�����Ķ���
	 */
	public static void testInsertLinks() throws Exception {
		Pet pet = new Pet();
		pet.setName("СС��");
		Master master = new Master();
		master.setName("СС��");
		pet.setMaster(master);
		
		out.println(dao.insertLinks(pet, "master"));
	}
	
	/**
	 * ��fetch�������Ķ���
	 */
	public static void testFetch() throws Exception {
		Pet pet = dao.fetch(Pet.class, "С��");
		dao.fetchLinks(pet, "master");
		out.println(pet.getMaster().getName());
	}
	
	/**
	 * ����
	 */
	public static void testUpdate() throws Exception {
		// ͬʱ����pet��master
		// dao.updateWith(pet, "master");
		
		// ��������master
		// dao.updateLinks(pet, "master");
	}
	
	/**
	 * ɾ��
	 */
	public static void testDelete() throws Exception {
		// ͬʱɾ��pet��master
		// dao.deleteWith(pet, "master");
		
		// ����ɾ��master��ÿ��һ��SQL��
		// dao.deleteLinks(pet, "master");
		
		// ���master��һ��SQL��
		// dao.clearLinks(pet, "master");
	}
}
