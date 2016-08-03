package com.casecollection.backend.framework.cache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class RedisPoolConfigHandler {


    /**
     * 从配置中读取redis配置
     * 
     * @param configPath
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<RedisPoolConfig> parseXmlConfig(String configPath) {
        configPath = configPath.trim();

        List<RedisPoolConfig> result = new ArrayList<RedisPoolConfig>();

        InputStream fin = null;
        try {
            if (configPath.startsWith("file:")) {
                String[] fileStr = configPath.split("file:");
                fin = new FileInputStream(fileStr[1]);
            } else if (configPath.startsWith("classpath:")) {
                String[] classpathStr = configPath.split("classpath:");
                fin = RedisPoolConfigHandler.class.getClassLoader().getResourceAsStream(classpathStr[1]);
            } else {
                fin = new FileInputStream(configPath);
            }

            SAXReader reader = new SAXReader();
            Document doc;
            try {
                doc = reader.read(fin);
                Element root = doc.getRootElement();
                for (Iterator<Element> i = root.elementIterator("pool"); i.hasNext();) {
                    RedisPoolConfig config = parseXmlPoolNode(i.next());
                    result.add(config);
                }
            } catch (DocumentException e) {
                throw new RedisInitException("redis config document parse error:", e);
            } catch (Exception e) {
                throw new RedisInitException("redis config parse error:", e);
            }
        } catch (FileNotFoundException e) {
            throw new RedisInitException("redis配置读取异常", e);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                }
            }
        }

        return result;
    }

    private static RedisPoolConfig parseXmlPoolNode(Element poolEle) {
        RedisPoolConfig config = new RedisPoolConfig();

        config.setPoolId(poolEle.attributeValue("id"));

        String maxIdle = getSingleNodeText(poolEle, "maxIdle1");
        if (StringUtils.isNotBlank(maxIdle)) {
            config.setMaxIdle(Integer.parseInt(maxIdle.trim()));
        }
        String maxTotal = getSingleNodeText(poolEle, "maxTotal");
        if (StringUtils.isNotBlank(maxTotal)) {
            config.setMaxTotal(Integer.parseInt(maxTotal.trim()));
        }
        String maxWait = getSingleNodeText(poolEle, "maxWait");
        if (StringUtils.isNotBlank(maxWait)) {
            config.setMaxWait(Integer.parseInt(maxWait.trim()));
        }
        String testOnBorrow = getSingleNodeText(poolEle, "testOnBorrow");
        if (StringUtils.isNotBlank(testOnBorrow)) {
            config.setTestOnBorrow("1".equals(testOnBorrow.trim()));
        }
        String setTestOnReturn = getSingleNodeText(poolEle, "setTestOnReturn");
        if (StringUtils.isNotBlank(setTestOnReturn)) {
            config.setTestOnReturn("1".equals(setTestOnReturn.trim()));
        }

        @SuppressWarnings("unchecked")
        List<Element> serverEles = poolEle.selectNodes("servers/server");
        List<String> servers = new ArrayList<String>();
        for (int i = 0; i < serverEles.size(); i++) {
            servers.add(serverEles.get(i).getText());
        }
        config.setServers(servers);

        return config;
    }

    private static String getSingleNodeText(Element elem, String key) {
        Node node = elem.selectSingleNode(key);
        if (null != node) {
            return node.getText();
        }
        return null;
    }

}
