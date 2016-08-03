package com.casecollection.backend.framework.cache.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.casecollection.backend.framework.cache.RedisClientProxy;
import com.casecollection.backend.framework.cache.RedisPoolFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClientProxyImpl implements RedisClientProxy {

    private ShardedJedisPool pool;

    public RedisClientProxyImpl(String poolName) {
        pool = RedisPoolFactory.getInstance().getPool(poolName);
    }

    /**
     * 向缓存中设置字符串内容
     * 
     * @param key
     * @param value
     * @param expiredMin
     *            过期时间，单位分钟
     * @return
     */
    public boolean set(String key, String value, int expiredMin) {

        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.set(key, value);
            if (expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }

        } catch (Exception e) {
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    /**
     * 向缓存中设置对象
     * 
     * @param key
     * @param value
     * @param expiredMin
     *            过期时间，单位分钟
     * @return
     */
    public boolean set(String key, Object value, int expiredMin) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.set(key, JSON.toJSONString(value));
            if (expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    /**
     * 删除缓存中得对象，根据key
     * 
     * @param key
     * @return
     */
    public boolean delete(String key) {

        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.del(key);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    /**
     * 根据key 获取内容
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            String value = client.get(key);
            return value;
        } catch (Exception e) {
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    /**
     * 根据key 获取对象
     * 
     * @param key
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        ShardedJedis client = null;

        try {
            client = pool.getResource();
            String value = client.get(key);
            return JSON.parseObject(value, clazz);
        } catch (Exception e) {
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }
 
    public <T> T getHasException(String key, Class<T> clazz) throws Exception{
	    	ShardedJedis client = null;
	    	try {
	    		client = pool.getResource();
	    		String value = client.get(key);
	    		return JSON.parseObject(value, clazz);
	    	} finally {
	    		if (null != client) {
	    			client.close();
	    		}
	    	}
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        ShardedJedis client = null;

        try {
            client = pool.getResource();
            String value = client.get(key);
            return JSON.parseArray(value, clazz);
        } catch (Exception e) {
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    public Long incr(String key, int expiredMin) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            Long value = client.incr(key);
            if (null != value && value == 1) {
                if (expiredMin > 0) {
                    client.expire(key, 60 * expiredMin);
                }
            }
            return value;
        } catch (Exception e) {
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }

    }

    // public <T> List<T> getMap(String key, Class<T> clazz) {
    // ShardedJedis client = null;
    //
    // try {
    // client = pool.getResource();
    // String value = client.get(key);
    // return JSON.
    // } catch (Exception e) {
    // e.printStackTrace();
    // return null;
    // } finally {
    // if (null != client) {
    // client.close();
    // }
    // }
    // }
}