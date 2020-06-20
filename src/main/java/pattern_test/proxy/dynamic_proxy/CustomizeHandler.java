package pattern_test.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: 处理执行逻辑的类
 *
 * @author Baltan
 * @date 2019-04-02 15:37
 */
public class CustomizeHandler implements InvocationHandler {

    private Object target;

    public CustomizeHandler(Class klass) {
        try {
            this.target = klass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前操作……");
        Object result = method.invoke(target, args);
        System.out.println("代理后操作……");
        return result;
    }

    /**
     * 创建代理类
     *
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                this);
    }
}
