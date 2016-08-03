package com.casecollection.backend.framework.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casecollection.backend.framework.cache.impl.RedisClientProxyImpl;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisPoolFactory {

    // 多个jedis pool
    private Map<String, ShardedJedisPool> poolMap = new HashMap<String, ShardedJedisPool>();

    // 客户端
    private static Map<String, RedisClientProxy> redisClientProxyMap = new HashMap<String, RedisClientProxy>();

    private static RedisPoolFactory instance;

    /**
     * 初始化工厂类 传入的配置文件路径,支持两种类型的文件路径:
     * 
     * <pre>
     *        1. file:filePath 通过文件路径查找
     *        2. classpath: path  通过classpath查找
     * </pre>
     * 
     * @return
     */

    public synchronized static RedisPoolFactory init(String configureFilePath) {
        instance = new RedisPoolFactory(configureFilePath);
        return instance;
    }

    private RedisPoolFactory(String configureFilePath) {
        List<RedisPoolConfig> poolCfgs = RedisPoolConfigHandler.parseXmlConfig(configureFilePath);
        for (RedisPoolConfig config : poolCfgs) {
            poolMap.put(config.getPoolId(), createPoolByCfg(config));
        }
    }

    /**
     * 根据pool name获取redis客户端代理
     * 
     * @param poolName
     * @return
     */
    public ShardedJedisPool getPool(String poolName) {
        ShardedJedisPool pool = poolMap.get(poolName);
        if (null == pool) {
            throw new RedisInitException("未读取到正确的配置： poolId:" + poolName);
        }
        return pool;
    }

    // 根据客户端配置，创建pool
    private ShardedJedisPool createPoolByCfg(RedisPoolConfig poolConfig) {

        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(poolConfig.getMaxIdle());
        config.setMaxTotal(poolConfig.getMaxTotal());
        config.setMaxWaitMillis(poolConfig.getMaxWait());
        config.setTestOnBorrow(poolConfig.isTestOnBorrow());
        config.setTestOnReturn(poolConfig.isTestOnReturn());

        // 生成多机连接信息列表
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        for (String server : poolConfig.getServers()) {
            // 服务器配置格式：127.0.0.1:6379
            String[] serverName = server.split(":");
            shards.add(new JedisShardInfo(serverName[0], Integer.parseInt(serverName[1])));
        }

        // 生成连接池配置信息
        ShardedJedisPool pool = null;
        if (shards.size() > 0) {
            pool = new ShardedJedisPool(config, shards);
        } else {
        }

        return pool;
    }

    // 获取客户端
    public RedisClientProxy getClientProxy(String poolName) {
        if (redisClientProxyMap.containsKey(poolName)) {
            return redisClientProxyMap.get(poolName);
        }
        RedisClientProxy clientProxy = new RedisClientProxyImpl(poolName);
        redisClientProxyMap.put(poolName, clientProxy);
        return clientProxy;
    }

    // 释放资源
    public void close() {
        if (null != poolMap) {
            for (Map.Entry<String, ShardedJedisPool> entry : poolMap.entrySet()) {
                entry.getValue().close();
            }
            poolMap.clear();
        }
    }

    public static RedisPoolFactory getInstance() {
        return instance;
    }

}