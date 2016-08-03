package com.casecollection.backend.framework.cache;

import java.util.List;

public abstract class AbstractRedisProxy implements RedisClientProxy{
    public final static String SYSTEM_APP_NAME = "INTELI_ANS";
	/**
	 * 获得针对于backend项目的缓存的key
	 * @param key
	 * @return
	 */
	public  String getPrefixKey(String key){
		StringBuffer sBuffer  = new StringBuffer(SYSTEM_APP_NAME);
		sBuffer.append("_");
		sBuffer.append(key);
		return sBuffer.toString();
	}

	@Override
	public boolean set(String key, String value, int expiredMin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean set(String key, Object value, int expiredMin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getHasException(String key, Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incr(String key, int expiredMin) {
		// TODO Auto-generated method stub
		return null;
	}


}
