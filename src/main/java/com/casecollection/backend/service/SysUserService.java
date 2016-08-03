package com.casecollection.backend.service;

import com.casecollection.backend.framework.bean.UserSession;
import com.casecollection.backend.model.SysUser;
import com.casecollection.backend.model.vo.SysUserVo;
import com.casecollection.backend.util.Pagination;

/**
 * Created by zuodeng on 16/3/8.
 */
public interface SysUserService {

    /**
     * 分页查询用户
     * @param userVo
     * @return
     */
    Pagination<SysUserVo> findUser(SysUserVo userVo,Pagination<SysUserVo> pg);

    /**
     * 新增用户
     * @param userVo
     * @return
     */
    String insertUser(SysUserVo userVo);

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    String updateUser(SysUserVo userVo);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(Long id,String name);

    /**
     * 获取用户
     * @param id
     * @return
     */
    SysUser getUser(Long id);

    /**
     * 设置用户状态
     * @param userVo
     * @return
     */
    int updateStatus(SysUserVo userVo);

    /**
     * 重置用户密码
     * @param userVo
     * @return
     */
    String resetPassword(SysUserVo userVo,String newPassword);

    /**
     * 登录服务
     * @param userVo
     * @return
     */
    String login(SysUserVo userVo,UserSession userSession);

    /**
     * 找回用户密码
     * @param userVo
     * @return
     */
    String updatePassword(SysUserVo userVo,String newPassword);
    
}
