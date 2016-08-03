package com.casecollection.backend.controller;

import com.casecollection.backend.framework.bean.UserSession;
import com.casecollection.backend.model.SysUser;
import com.casecollection.backend.model.vo.SysUserVo;
import com.casecollection.backend.service.SysUserService;
import com.casecollection.backend.util.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuodeng on 16/3/12.
 */
@Controller
@RequestMapping(value = "/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/toList",method = RequestMethod.GET)
    public String toList(Model model,HttpServletRequest request){
        return "/backend/user/list";
    }

    /**
     * 分页查询示例
     * js参考
     * @param vo
     * @param pg
     * @return
     */
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    @ResponseBody
    public Pagination<SysUserVo> findUser(SysUserVo vo,Pagination<SysUserVo> pg,HttpServletRequest request){
        Object obj = request.getSession().getAttribute("user");
        if(obj != null) {
            UserSession user = (UserSession) obj;
            vo.setDataLevel(user.getDataLevel());
        }
        Object sCode = request.getSession().getAttribute("sCode");
        if(sCode == null) {
            return null;
        }
        vo.setSchoolCode(sCode.toString());
        vo.setPagination(pg);
        Pagination<SysUserVo> schoolVoList = sysUserService.findUser(vo, pg);
        return schoolVoList;
    }

    @RequestMapping(value = "/toAdd",method = RequestMethod.GET)
    public String toAdd(){
        return "/backend/user/add";
    }

    @RequestMapping(value = "/toUpdate",method = RequestMethod.GET)
    public String toUpdate(Long id,Model model){
        SysUser sysUser = sysUserService.getUser(id);
        model.addAttribute("user",sysUser);
        return "/backend/user/update";
    }

    @RequestMapping(value = "/manager",method = RequestMethod.GET)
    public String manager(Model model,HttpServletRequest request){
        Object obj = request.getSession().getAttribute("user");
        UserSession user = (UserSession) obj;
        SysUser sysUser = sysUserService.getUser(user.getId());
        model.addAttribute("user",sysUser);
        return "/backend/user/manager";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map addSchool(SysUserVo vo,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            Object obj = request.getSession().getAttribute("user");
            vo.setCreateTime(new Date());
            vo.setModifyTime(new Date());
            if(obj != null) {
                UserSession user = (UserSession) obj;
                vo.setCreator(user.getName());
                vo.setModifier(user.getName());
            }
            Object sCode = request.getSession().getAttribute("sCode");
            if(sCode == null) {
                return null;
            }
            vo.setSchoolCode(sCode.toString());
            String message = sysUserService.insertUser(vo);
            if(StringUtils.isNotEmpty(message)){
                map.put("error", message);
                return map;
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "新增失败，请联系运营人员");
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map updateUser(SysUserVo vo,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            Object obj = request.getSession().getAttribute("user");
            vo.setModifyTime(new Date());
            if(obj != null) {
                UserSession user = (UserSession) obj;
                vo.setModifier(user.getName());
            }
            Object sCode = request.getSession().getAttribute("sCode");
            if(sCode == null) {
                return null;
            }
            vo.setSchoolCode(sCode.toString());
            String message = sysUserService.updateUser(vo);
            if(StringUtils.isNotEmpty(message)){
                map.put("error",message);
                return map;
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "修改失败，请联系运营人员");
        }
        return map;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map deleteSchool(Long id,HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Object obj = request.getSession().getAttribute("user");
            String loginName = null;
            if(obj != null) {
                UserSession user = (UserSession) obj;
                loginName = user.getName();
            }
            sysUserService.deleteUser(id, loginName);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "删除失败，请联系运营人员");
        }
        return map;
    }

    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public Map deleteSchool(SysUserVo vo,HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Object obj = request.getSession().getAttribute("user");
            if(obj != null) {
                UserSession user = (UserSession) obj;
                vo.setModifier(user.getName());
            }
            sysUserService.updateStatus(vo);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "操作失败，请联系运营人员");
        }
        return map;
    }

    @RequestMapping(value = "/toResetPassword",method = RequestMethod.GET)
    public String toResetPassword(Model model,HttpServletRequest request){
        Object obj = request.getSession().getAttribute("user");
        UserSession user = (UserSession) obj;
        SysUser sysUser = sysUserService.getUser(user.getId());
        model.addAttribute("user", sysUser);
        return "/backend/user/resetPassword";
    }

    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public Map resetPassword(SysUserVo vo,String newPassword,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            String message = sysUserService.resetPassword(vo, newPassword);
            if(!StringUtils.isEmpty(message)){
                map.put("error", message);
                return map;
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "操作失败，请联系运营人员");
        }
        return map;
    }

    @RequestMapping("/findPassword/toFindPassword")
    public String toFindPassword() {
        return "/backend/user/findPassword";
    }



    @RequestMapping(value = "/findPassword/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map updatePassword(SysUserVo vo,String newPassword,String valicode,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            String message = sysUserService.updatePassword(vo, newPassword);
            if(!StringUtils.isEmpty(message)){
                map.put("error", message);
                return map;
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "操作失败，请联系运营人员");
        }
        return map;


    }

}
