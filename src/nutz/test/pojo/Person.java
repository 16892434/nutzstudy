package nutz.test.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_person")
public class Person {

	@Column		// ��ʾһ����
	@Id			// ��ʾ����Id
	private int id;
	@Column
	@Name		// ��ʾ������������ʶ�����
	private String name;
	@Column
	private int age;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
