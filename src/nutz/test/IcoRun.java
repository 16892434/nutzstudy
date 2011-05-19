package nutz.test;

import nutz.test.pojo.Person;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

public class IcoRun {

	public static void main(String [] args) {
		Ioc ioc = new NutIoc(new JsonLoader("ioc.js"));
		Person p = ioc.get(Person.class, "ppp");
		System.out.println(p.getName());
	}
	
}
