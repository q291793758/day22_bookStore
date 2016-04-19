package cn.itcast.factory;

import cn.itcast.domain.Privilege;
import cn.itcast.domain.User;
import cn.itcast.service.BussinessService;
import cn.itcast.service.impl.BussinessServiceImpl;
import cn.itcast.utils.Permission;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ServiceFactory {
    private static ServiceFactory ourInstance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
    }

    public BussinessService createBussinessService(User user) {

        BussinessServiceImpl service = new BussinessServiceImpl();
        return (BussinessService) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), service.getClass().getInterfaces()
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //得到web层调用的方法
                        String methodName = method.getName();

                        //反射出真实对象上的相应的方法,检查真实对象方法上有没有注解
                        Method realMethod = service.getClass().getMethod(methodName, method.getParameterTypes());
                        if (realMethod == null) {
                            return null;
                        }
                        Permission permission = realMethod.getAnnotation(Permission.class);
                        //没有权限拦截注解,执行方法
                        if (permission == null) {
                            return method.invoke(service, args);

                        }
                        //有权限拦截 得到访问方法需要的权限
                        Privilege p = new Privilege(permission.value());
                        //检查当前登录用户有没有对应权限 //AppContext ThreadLocal 将user绑定当前线程传递
                        //得到用户所有权限
                        if (user == null) {
                            throw new SecurityException("请先登录!");
                        }
                       List<Privilege> list= service.getUserAllPrivilege(user);
                        //若用户拥有该权限
                        if (list.contains(p)) {
                           return method.invoke(service,args);
                        }

                       throw new SecurityException("没有权限执行该操作!");
                    }
                });

    }
}
