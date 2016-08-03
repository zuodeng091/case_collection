package com.casecollection.backend.framework.bean;

public class ApplicationUserContext {

	private static final ThreadLocal<UserSession> session = new ThreadLocal<UserSession>();

	public static UserSession getUser() {
		return session.get();
	}

	public static void setUser(UserSession user) {
		session.set(user);
	}
	public static void clear() {
		session.set(null);
	}
    
    /**
     * 获取登陆用户id
     * @return
     */
    public static Long  getUserId() {
        UserSession tmp = session.get();
        if(tmp==null){
            return null;
        }
        return tmp.getId();
    }
	
	/**
     * 获取登陆用户名称
     * @return
     */
	public static String  getUserName() {
        UserSession tmp = session.get();
        if(tmp==null){
            return "";
        }
        return tmp.getName();
    }
}
