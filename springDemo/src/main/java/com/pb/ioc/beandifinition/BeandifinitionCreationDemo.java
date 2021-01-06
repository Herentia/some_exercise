package com.pb.ioc.beandifinition;

import com.pb.entity.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author haohan
 * @date 2020/12/10 14:40
 */
public class BeandifinitionCreationDemo {

    public static void main(String[] args) {
        // 通过BeanDefinitionBuilder创建beanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        beanDefinitionBuilder.addPropertyValue("name", "xx")
                .addPropertyValue("age", "18");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        
        // 通过AbstractBeanDefinition创建beanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        //设置属性值
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "yy")
                .add("age", "19");

        genericBeanDefinition.setPropertyValues(propertyValues);
    }

}
