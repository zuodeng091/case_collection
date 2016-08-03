package com.casecollection.backend.model.vo;

import com.casecollection.backend.model.SysUser;
import com.casecollection.backend.util.Pagination;

/**
 * Created by zuodeng on 16/3/8.
 */
public class SysUserVo extends SysUser{

    private Pagination<SysUserVo> pagination;

    public Pagination<SysUserVo> getPagination() {
        return pagination;
    }

    public void setPagination(Pagination<SysUserVo> pagination) {
        this.pagination = pagination;
    }

}
