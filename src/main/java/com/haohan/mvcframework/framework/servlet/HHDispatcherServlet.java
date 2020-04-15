package com.haohan.mvcframework.framework.servlet;

import com.haohan.mvcframework.framework.annotation.HHAutowired;
import com.haohan.mvcframework.framework.annotation.HHController;
import com.haohan.mvcframework.framework.annotation.HHRequestMapping;
import com.haohan.mvcframework.framework.annotation.HHService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author haohan
 * 04/15/2020 - 10:04 上午
 * 此类作为启动入口
 */
public class HHDispatcherServlet extends HttpServlet {

    //跟web.xml中的param-name的值一致
    private static final String LOCATION = "contextConfigLocation";
    //保存所有的配置信息
    private Properties p = new Properties();
    //保存所有被扫描到的相关类名
    private List<String> classNames = new ArrayList<String>();
    //核心IOC容器，保存所有初始化的Bean
    private Map<String, Object> ioc = new HashMap<String, Object>();
    //保存所有的URL和方法的映射关系
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    public HHDispatcherServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * 处理业务请求
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //开始匹配到对应的方法
            doDispatch(req, resp);
        } catch (IOException e) {
            //如果匹配过程中出现异常，将异常信息打印出去
            resp.getWriter().write("500 Exception, Details:\r\n" + Arrays.toString(e.getStackTrace())
            .replaceAll("\\[|\\]", "")
            .replaceAll(",\\s", "\r\n"));
        }
    }

    /**
     * 初始化加载配置文件
     * IOC容器初始化
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        //1、加载配置文件
        doLoadConfig(config.getInitParameter(LOCATION));

        //2、扫描所有相关的类
        doScanner(p.getProperty("scanPackage"));

        //3、初始化所有相关类的实例，并保存到IOC容器
        doInstance();

        //4、依赖注入DI
        doAutowired();

        //5、构造HandlerMapping
        initHandlerMapping();


        //6、等待请求，匹配URL，定位 方法，反射调用执行

        System.out.println("haohan mvcframework is init finish！");
    }

    /**
     * 读取配置文件
     * @param location 文件路径
     */
    private void doLoadConfig(String location) {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(location);
            //读取配置文件
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据配置文件获取的包名扫描所有Class文件
     */
    private void doScanner(String packageName) {
        //将所有的包路径转换为文件路径
        URL url = this.getClass().getClassLoader()
                .getResource("/" + packageName.replaceAll("\\.", "/"));
        System.out.println("文件路径为===》》》" + url);
        //获取文件路径下面的所有文件
        assert url != null;
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            //如果是文件夹则继续递归查找
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    /**
     * 将首写字母小写
     */
    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 初始化相关类并保存进IOC
     */
    private void doInstance() {
        if (classNames.size() == 0) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(HHController.class)) {
                    //默认将首写字母小写作为beanName
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(HHService.class)) {
                    HHService service = clazz.getAnnotation(HHService.class);
                    String beanName = service.value();
                    //如果设置了名字，就是自己设置的
                    if (!"".equals(beanName.trim())) {
                        ioc.put(beanName, clazz.newInstance());
                        return;
                    }

                    //如果没有设置名字，则以类的接口首写字母小写为名字
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        ioc.put(lowerFirstCase(i.getName()), clazz.newInstance());
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Di依赖注入
     */
    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //获取实例对象的所有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(HHAutowired.class)) {
                    continue;
                }

                HHAutowired autowired = field.getAnnotation(HHAutowired.class);
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                //设置私有属性的访问权限
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    /**
     * 映射处理器，将HHRequestMapping 和 Method进行关联并保存关系
     */
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(HHController.class)) {
                continue;
            }
            String baseUrl = "";
            //获取Controller的url配置
            if (clazz.isAnnotationPresent(HHRequestMapping.class)) {
                HHRequestMapping requestMapping = clazz.getAnnotation(HHRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //获取Method的url配置
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                //没有HHRequestMapping注解的直接忽略
                if (!method.isAnnotationPresent(HHRequestMapping.class)) {
                    continue;
                }

                //存在在获取URL映射并保存
                HHRequestMapping requestMapping = method.getAnnotation(HHRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                handlerMapping.put(url, method);
                System.out.println("mapped===>>>" + url + "=" + method);
            }
        }
    }

    /**
     * 执行
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (this.handlerMapping.isEmpty()) {
            return;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        //判断映射地址是否存在，不存在则提示并返回
        if (!this.handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 NOT Found!!!");
            return;
        }

        Method method = this.handlerMapping.get(url);
        //获取方法的参数列表
        Class<?>[] parameterTypes = method.getParameterTypes();
        //保存参数值
        Object[] paramValues = new Object[parameterTypes.length];
        //获取请求的参数
        Map<String, String[]> params = req.getParameterMap();
        //方法的参数列表
        for (int i = 0; i < paramValues.length; i++) {
            //根据参数名字做某些处理
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == HttpServletRequest.class) {
                //参数类型已明确，这边强转类型
                paramValues[i] = req;
                continue;
            } else if (parameterType == HttpServletResponse.class) {
                paramValues[i] = resp;
                continue;
            } else if (parameterType == String.class) {
                for (Map.Entry<String, String[]> param : params.entrySet()) {
                    String value = Arrays.toString(param.getValue())
                            .replaceAll("\\[|\\]", "")
                            .replaceAll(",\\s", ",");
                    paramValues[i] = value;
                }
            }
        }
        try {
            String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
            //利用反射机制来调用
            method.invoke(this.ioc.get(beanName), paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
