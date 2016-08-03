package com.casecollection.backend.framework.config;

import java.util.Properties;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/**
 * 自定义spring 加载配置属性类
 * @Create_by Ranger
 * @Create_Date 2015年6月15日上午10:30:45
 */
public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {

    private static Properties ctxProperties;

    private String[] encryptPropNames = {"jdbc.password", "jdbc.bi.password"};


    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        return propertyValue;
    }
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        ctxProperties = props;
    }

    public static String getProperty(String name) {
        return ctxProperties.getProperty(name);
    }

    private boolean isEncryptProp(String propertyName) {
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName))
                return true;
        }
        return false;
    }
}

