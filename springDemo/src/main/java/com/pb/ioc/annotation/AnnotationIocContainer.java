package com.pb.ioc.annotation;

import com.pb.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @author haohan
 * @date 2020/12/9 15:18
 */
//@Configuration
public class AnnotationIocContainer {

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationIocContainer.class);

        //启动容器
        applicationContext.refresh();
        lookupCollectionByType(applicationContext);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setName("大狗");
        user.setAge("18");
        user.setAddress("shanghai");
        return user;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(beans);
        }
    }

}
