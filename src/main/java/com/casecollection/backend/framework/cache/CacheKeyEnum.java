package com.casecollection.backend.framework.cache;

public enum CacheKeyEnum {


    /**
     * 采购单物料缓存
     */
    ONLINE_KEFU("ONLINE_KEFU", Contants.ONE_HOUR),
    /**
     * 二级页面菜单
     */
    SCHOOLE_SUB_PAGE_MENU("SCHOOLE_SUB_PAGE_MENU", Contants.ONE_HOUR),
    /**
     * 二级页面菜单
     */
    SCHOOLE_OBJECT("SCHOOLE_OBJECT", Contants.ONE_HOUR),


    ;
    /** 缓存的KEY */
    private String key;
    /** 缓存超时时间，单位：分钟 */
    private Integer expireMins;

    private CacheKeyEnum(String key, Integer expireMins) {
        this.key = key;
        this.expireMins = expireMins;
    }

    /**
     * 返回key
     * 
     * @param values
     * @return
     */
    public String getKey(Object... values) {
        StringBuilder builder = new StringBuilder(key);
        for (Object val : values) {
            if (null != val) {
                builder.append("_").append(val);
            }
        }
        return builder.toString();
    }

    /**
     * 设置缓存的KEY
     * 
     * @param key
     *            缓存的KEY
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取缓存超时时间，单位：分钟
     * 
     * @return expireMins 缓存超时时间，单位：分钟
     */
    public Integer getExpireMins() {
        return expireMins;
    }

    /**
     * 设置缓存超时时间，单位：分钟
     * 
     * @param expireMins
     *            缓存超时时间，单位：分钟
     */
    public void setExpireMins(Integer expireMins) {
        this.expireMins = expireMins;
    }

    public static class Contants {

        public static final String KEY_PREFIX = "INTEILI_ANS";
        /** 1分钟 */
        public static final Integer ONE_MIN = 1;
        /** 6分钟 */
        public static final Integer SIX_MIN = 6;
        /** 1小时 */
        public static final Integer ONE_HOUR = 60;
        /** 1天 */
        public static final Integer ONE_DAY = 60 * 24;
        
        public static final Integer NAPOS_RESTAURANT_EXPIREDAT = 5;
    }
}