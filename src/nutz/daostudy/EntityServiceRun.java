package nutz.daostudy;

public class EntityServiceRun extends Run {

	/**
	  
	  ԭ����д����
	 Pet pet = dao.fetch(Pet.class,"XiaoBai");

	 ʹ�÷������
	 IdNameEntityService<Pet> pets = new IdNameEntityService<Pet>(dao){};
	 Pet pet = pets.fetch("XiaoBai");

	 ��� POJO �������� @Id �������� @Name����ô�ʺϲ��� IdNameEntityService 
	 ��� POJO �������� @Id����ô�ʺϲ��� IdEntityService 
	 ��� POJO �������� @Name����ô�ʺϲ��� NameEntityService 
	 ��� POJO ��û������ @Id ��û������ @Name����ô�ʺϲ��� EntityService 

	 */
	
}
