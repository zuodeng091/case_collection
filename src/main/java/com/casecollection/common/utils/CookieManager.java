package com.casecollection.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieManager {
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CookieManager(HttpServletRequest req, HttpServletResponse res) {
		request = req;
		response = res;
	}
	
	/**
	 * 获取远程IP地址
	 * 获取客户端真实IP(代理服务器会做代理)
	 * @return
	 */
	public String getRemoteIp() {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 设置cookie
	 * @param name
	 * @param value
	 * @param domain
	 * @param expire
	 */
	public void setCookie(String name, String value, String domain, int expire) {
		try {
			if(StringUtils.isNotEmpty(value)){
                value = URLEncoder.encode(value, "utf-8");
            }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie cookie = new Cookie(name, value);
		if(StringUtils.isNotEmpty(domain)){
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		if (expire >= 0) {
			cookie.setMaxAge(expire);
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 获取某个cookie值
	 *@param name
	 *@return
	 */
	public String getCookieValue(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			try{
				if (cookie.getName().equalsIgnoreCase(name)) {
					if(StringUtils.isNotEmpty(cookie.getValue())){
						return URLDecoder.decode(cookie.getValue(), "utf-8");
					}else{
						continue;
					}
				}
			}catch (UnsupportedEncodingException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	public void deleteCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return;
		
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equalsIgnoreCase(name)) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		
	}
	
	
}
