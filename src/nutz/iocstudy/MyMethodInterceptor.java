package nutz.iocstudy;

import static java.lang.System.out;
import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;

public class MyMethodInterceptor implements MethodInterceptor {

	@Override
	public void filter(InterceptorChain chain) throws Throwable {
		out.println("�ף��������ִ���ˣ�");
		chain.doChain();
		out.println("�������ִ�����ˣ�");
	}

}
