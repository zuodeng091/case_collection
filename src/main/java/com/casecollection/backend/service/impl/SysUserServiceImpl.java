package com.casecollection.backend.service.impl;

import com.casecollection.backend.constants.enums.DeleteEnum;
import com.casecollection.backend.dao.SysUserMapper;
import com.casecollection.backend.framework.bean.UserSession;
import com.casecollection.backend.model.SysUser;
import com.casecollection.backend.model.vo.SysUserVo;
import com.casecollection.backend.service.SysUserService;
import com.casecollection.backend.util.MD5Util;
import com.casecollection.backend.util.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zuodeng on 16/3/8.
 */
@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 分页查询用户
     *
     * @param userVo
     * @param pg
     * @return
     */
    @Override
    public Pagination<SysUserVo> findUser(SysUserVo userVo, Pagination<SysUserVo> pg) {
        try {
            int count = sysUserMapper.findUserCount(userVo);
            if(count <= 0){
                return pg;
            }
            List<SysUserVo> sysUserVoList = new ArrayList<>();
            List<SysUser> userList = sysUserMapper.findUser(userVo);
            for(SysUser sysUser : userList){
                SysUserVo sysUserVo = new SysUserVo();
                BeanUtils.copyProperties(sysUser, sysUserVo);
                sysUserVoList.add(sysUserVo);
            }
            pg.setData(sysUserVoList);
            pg.setTotalRows(count);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pg;
    }

    /**
     * 新增用户
     *
     * @param userVo
     * @return
     */
    @Override
    public String insertUser(SysUserVo userVo) {
        try{
            List<SysUser> user = sysUserMapper.getUserByName(userVo);
            if(!CollectionUtils.isEmpty(user)){
                return "该用户名已经存在";
            }
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userVo,sysUser);
            sysUser.setPassword(MD5Util.EncoderByMd5(sysUser.getPassword()));
            sysUserMapper.insertSelective(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 修改用户
     *
     * @param userVo
     * @return
     */
    @Override
    public String updateUser(SysUserVo userVo) {
        try{
            List<SysUser> user = sysUserMapper.getUserByName(userVo);
            if(!CollectionUtils.isEmpty(user)){
                return "该用户名已经存在";
            }
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userVo,sysUser);
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public int deleteUser(Long id,String name) {
        int i = 0;
        try{
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setIsDelete(DeleteEnum.DELETE.getValue());
            sysUser.setModifier(name);
            sysUser.setModifyTime(new Date());
            i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    @Override
    public SysUser getUser(Long id) {
        SysUser sysUser = null;
        try {
            sysUser = sysUserMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sysUser;
    }

    /**
     * 设置用户状态
     *
     * @param userVo
     * @return
     */
    @Override
    public int updateStatus(SysUserVo userVo) {
        int i = 0;
        try{
            SysUser sysUser = new SysUser();
            sysUser.setId(userVo.getId());
            sysUser.setStatus(userVo.getStatus());
            sysUser.setModifier(userVo.getLoginName());
            sysUser.setModifyTime(new Date());
            i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 重置用户密码
     *
     * @param userVo
     * @return
     */
    @Override
    public String resetPassword(SysUserVo userVo,String newPassWord) {
        try {
            SysUser user = sysUserMapper.selectByPrimaryKey(userVo.getId());
            if(!MD5Util.EncoderByMd5(userVo.getPassword()).equals(user.getPassword())){
                return "请输入正确的旧密码";
            }
            SysUser sysUser = new SysUser();
            sysUser.setId(userVo.getId());
            sysUser.setPassword(MD5Util.EncoderByMd5(newPassWord));
            sysUser.setModifyTime(new Date());
            sysUser.setModifier(user.getLoginName());
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 登录服务
     *
     * @param userVo
     * @return
     */
    @Override
    public String login(SysUserVo userVo,UserSession userSession) {
        try {
            if(StringUtils.isEmpty(userVo.getLoginName()) || StringUtils.isEmpty(userVo.getPassword())){
                return "请填写用户名或密码";
            }
            List<SysUser> sysUsers = sysUserMapper.getUserByName(userVo);
            if(CollectionUtils.isEmpty(sysUsers)){
                return "用户名不存在";
            }
            SysUser sysUser = sysUsers.get(0);
            if(!MD5Util.EncoderByMd5(userVo.getPassword()).equals(sysUser.getPassword())){
                return "用户密码错误";
            }
            if(sysUser.getStatus() == 2){
                return "该账号已被禁用";
            }
            userSession.setName(sysUser.getLoginName());
            userSession.setDataLevel(sysUser.getDataLevel());
            userSession.setCusLevel(sysUser.getCusLevel());
            userSession.setIsHandling(sysUser.getIsHandling());
            userSession.setsCode(sysUser.getSchoolCode());
            userSession.setId(sysUser.getId());
            userSession.setHandNum(sysUser.getHandlingNum());
            userSession.setRealName(sysUser.getRealName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 找回用户密码
     *
     * @param userVo
     * @return
     */
    @Override
    public String updatePassword(SysUserVo userVo,String newPassWord) {
        try {
            List<SysUser> sysUsers = sysUserMapper.getUserByName(userVo);
            if(CollectionUtils.isEmpty(sysUsers)){
                return "该用户不存在";
            }
            if(!userVo.getEmail().equals(sysUsers.get(0).getEmail())){
                return "邮箱与账号不匹配";
            }
            SysUser sysUser = new SysUser();
            sysUser.setId(sysUsers.get(0).getId());
            sysUser.setPassword(MD5Util.EncoderByMd5(newPassWord));
            sysUser.setModifyTime(new Date());
            sysUser.setModifier(userVo.getLoginName());
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
