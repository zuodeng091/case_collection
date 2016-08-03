package com.casecollection.backend.framework.cache.impl;

import com.casecollection.backend.framework.cache.AbstractRedisProxy;
import org.springframework.stereotype.Service;


@Service(value="baseRedisProxy")
public class BaseRedisProxy extends AbstractRedisProxy{
	
	
//	@Resource(name = "redis_data_pool")
//	private RedisClientProxy redisClientProxy;
//	@Override
//	public boolean set(String key, String value, int expiredMin) {
//		return redisClientProxy.set(getPrefixKey(key), value, expiredMin);
//	}
//
//	@Override
//	public boolean set(String key, Object value, int expiredMin) {
//		return redisClientProxy.set(getPrefixKey(key), value, expiredMin);
//	}
//	@Override
//	public boolean delete(String key) {
//		return redisClientProxy.delete(getPrefixKey(key));
//	}
//
//	@Override
//	public String get(String key) {
//	     return redisClientProxy.get(getPrefixKey(key));
//	}
//
//	@Override
//	public <T> T get(String key, Class<T> clazz) {
//		return redisClientProxy.get(getPrefixKey(key), clazz);
//	}
//
//	@Override
//	public <T> T getHasException(String key, Class<T> clazz) throws Exception {
//		return redisClientProxy.getHasException(getPrefixKey(key), clazz);
//	}
//
//	@Override
//	public <T> List<T> getList(String key, Class<T> clazz) {
//		return redisClientProxy.getList(getPrefixKey(key), clazz);
//	}
//
//	@Override
//	public Long incr(String key, int expiredMin) {
//		return redisClientProxy.incr(getPrefixKey(key), expiredMin);
//	}

}
