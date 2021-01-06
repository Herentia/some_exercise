package com.pb.ioc.overview;

import com.pb.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author haohan
 * @date 2020/11/16 9:57
 */
public class IocContainerDemo {

    public static void main(String[] args) {
        //配置 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        //加载配置文件
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean 定义数量为：" + beanDefinitionsCount);
        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(beans);
        }
    }

}
