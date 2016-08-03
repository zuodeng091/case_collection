package com.casecollection.backend.dao;

import com.casecollection.backend.model.SysUser;
import com.casecollection.backend.model.vo.SysUserVo;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 分页查询数量
     * @param sysUser
     * @return
     */
    int findUserCount(SysUserVo sysUser);

    /**
     * 分页查询用户,只能查询同级别或者低级别的
     * @param sysUser
     * @return
     */
    List<SysUser> findUser(SysUserVo sysUser);

    /**
     * 根据用户名查询用户
     * @param sysUse
     * @return
     */
    List<SysUser> getUserByName(SysUserVo sysUse);

}