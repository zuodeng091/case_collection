package com.casecollection.backend.framework.cache;

import java.util.List;

/**
 * redis基本操作
 */
public interface RedisClientProxy {

    /**
     * 向缓存中设置字符串内容
     * 
     * @param key
     * @param value
     * @param expiredMin
     *            过期时间，单位分钟
     * @return
     */
    public boolean set(String key, String value, int expiredMin);

    /**
     * 向缓存中设置对象
     * 
     * @param key
     * @param value
     * @param expiredMin
     *            过期时间，单位分钟
     * @return
     */
    public boolean set(String key, Object value, int expiredMin);

    public boolean delete(String key);

    public String get(String key);

    public <T> T get(String key, Class<T> clazz);
    
    /**
     * 根据key 获取对象 需要捕获异常
     * @param key
     * @param clazz
     * @return
     * @throws Exception
     */
    public <T> T getHasException(String key, Class<T> clazz) throws Exception;

    public <T> List<T> getList(String key, Class<T> clazz);

    /**
     * 自增，第一次自增时，设置超期时间(使用默认自增值)
     * 
     * @param key
     * @return
     */
    public Long incr(String key, int expiredMin);

}