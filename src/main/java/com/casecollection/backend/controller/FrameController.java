package com.casecollection.backend.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casecollection.backend.constants.MenuConstants;
import com.casecollection.backend.framework.bean.UserSession;
import com.casecollection.backend.model.vo.SysUserVo;
import com.casecollection.backend.service.SysUserService;
import com.casecollection.backend.system.Menu;
import com.casecollection.backend.util.DateUtil;

/**
 * @Desc 首页Controller
 * @author rocklee
 *
 */
@Controller
public class FrameController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping("/weixinApi")
    @ResponseBody
    public String weixinApi(String signature, Long timestamp, Integer nonce, String echostr) {
        
        return "redirect:/index";
    }
    
    @RequestMapping("/videoUploadCallback")
    @ResponseBody
    public Map<String, Object> videoUploadCallback(String fileName, String key) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("callback>>>>>>>"+key);
        map.put("state", "SUCCESS");
        String urlPrefix = "http://7xtoqx.com2.z0.glb.qiniucdn.com";
        if(urlPrefix != null && !urlPrefix.endsWith("/")) {
            urlPrefix += "/";
        }
        map.put("url", urlPrefix + key);
        map.put("title", fileName);
        map.put("original", fileName);
        return map;
    }

    /**
     * 后台首页
     */
    @RequestMapping("/index")
    public String toIndex(Model model) {
        return "/index";
    }
    
    /**
     * 登录
     * @throws IOException 
     */
    @RequestMapping("/loginForm")
    @ResponseBody
    public Map<String, Object> loginForm(SysUserVo userVo, HttpServletRequest request,HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        UserSession userSession = new UserSession();
        String message = sysUserService.login(userVo,userSession);
        if(StringUtils.isEmpty(message)){
            request.getSession().setAttribute("user",userSession);
            request.getSession().setAttribute("sCode",userSession.getsCode());
            map.put("success", true);
            return map;
        }
        map.put("error",message);
        return map;
    }

    /**
     * @Desc 获取用户拥有的菜单
     * @return String
     * @Create_by Ranger
     * @Create_Date 2015年5月21日上午1:15:45
     */
    @RequestMapping("/loadMenu")
    @ResponseBody
    public List<Menu> loadMenu(HttpServletRequest request) {
        UserSession user = (UserSession) request.getSession().getAttribute("user");
        if(user != null && user.getDataLevel() == -1) {
            return MenuConstants.getAdminMenus();
        }
        return MenuConstants.getSchoolMenus();
    }
	/**
	 * 跳转无权限页面
	 */
	@RequestMapping("/noPermisson")
	public String noPermission() {
		return "noPermission";
	}
	/**
	 * 跳转ajax无权限页面
	 */
	@RequestMapping("/ajax_noPermission")
    @ResponseBody
	public Map<String, String> ajaxNoPermission() {
        Map<String, String> noPermissionMessage = new HashMap<>();
        noPermissionMessage.put("error", "您没有访问权限，请申请开通");
        return noPermissionMessage;
	}
	/**
	 * frame 首页
	 */
	@RequestMapping("/frame")
	public String frame(HttpServletRequest request, Model model) {
		model.addAttribute("currentDate", DateUtil.getDate(new Date(), DateUtil.default_pattern_d));
		model.addAttribute("day", DateUtil.getWeek(new Date()));
		UserSession user = (UserSession) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        boolean isCs = false;
        //判断是否需要显示在线答疑
        if(user.getCusLevel() != 0 && user.getIsHandling() != null && user.getIsHandling() == 1){
            isCs = true;
        }
        model.addAttribute("isCs", isCs);
		return "/frame";
	}
	
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("sCode");
        //跳转到登录页面
        return "redirect:/index";
    }
	/**
	 * 侧边栏
	 */
	@RequestMapping("/sidebar")
	public String sidebar() {
		return "../layouts/sidebar";
	}
    
    @RequestMapping("/ue")
    public String ue(String action, String noCache, Model model) {
        model.addAttribute("action", action);
        model.addAttribute("noCache", noCache);
        return "redirect:/static/ueditor/jsp/controller.jsp";
    }
    
}