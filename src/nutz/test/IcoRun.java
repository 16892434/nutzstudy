package nutz.test;

import static java.lang.System.out;
import nutz.iocstudy.BaseIoc;
import nutz.test.pojo.Person;

public class IcoRun extends BaseIoc {

	public static void main(String [] args) {
		Person p = ioc.get(Person.class, "xiaobai");
		out.println(p.getName());
	}
	
}
