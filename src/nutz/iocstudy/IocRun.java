package nutz.iocstudy;

import static java.lang.System.out;
import nutz.test.pojo.Person;

public class IocRun extends BaseIoc {

	public static void main(String [] args) throws Exception {
		testIocGet();
	}
	
	/**
	 * IOC取对象信息
	 */
	public static void testIocGet() throws Exception {
		Person p = ioc.get(Person.class, "xiaobai");
		out.println(p.getName());

		// 指定了类型时，第一个参数可以为null
		Person hei1 = ioc.get(null, "xiaohei");
		Person hei2 = ioc.get(null, "xiaohei");
		out.println(hei1==hei2);
	}
}
