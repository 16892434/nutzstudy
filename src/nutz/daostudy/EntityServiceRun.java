package nutz.daostudy;

public class EntityServiceRun extends Run {

	/**
	  
	  原来的写法：
	 Pet pet = dao.fetch(Pet.class,"XiaoBai");

	 使用服务类后：
	 IdNameEntityService<Pet> pets = new IdNameEntityService<Pet>(dao){};
	 Pet pet = pets.fetch("XiaoBai");

	 如果 POJO 即声明了 @Id 又声明了 @Name，那么适合采用 IdNameEntityService 
	 如果 POJO 仅声明了 @Id，那么适合采用 IdEntityService 
	 如果 POJO 仅声明了 @Name，那么适合采用 NameEntityService 
	 如果 POJO 即没声明了 @Id 又没声明了 @Name，那么适合采用 EntityService 

	 */
	
}
