package nutz.iocstudy;

import static java.lang.System.out;
import nutz.daostudy.Run;

import org.nutz.ioc.aop.Aop;

public class AopRun extends Run {

	public static void main(String [] args) throws Exception {
		new AopRun().testInterceptor();
	}
	
	@Aop("myMethodInterceptor")
	public void testInterceptor() throws Exception {
		out.println("没人知道我在干什么！");
	}
}
