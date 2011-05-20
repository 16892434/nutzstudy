package nutz.iocstudy;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

public class BaseIoc {

	public static final Ioc ioc = new NutIoc(new JsonLoader("ioc.js"));
	
}
