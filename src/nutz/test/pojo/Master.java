package nutz.test.pojo;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_master")
public class Master {

	@Column
	@Id
	private int id;
	@Column
	@Name
	private String name;
	@Many(target=Pet.class, field="masterId")
	private List<Pet> pets;
	// private Pet[] pets;
	// @Many(target=Pet.class, field="masterId", key="name")
	// private Map<String, pet> pets;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
}
