package com.casecollection.backend.model.vo;


import com.casecollection.backend.model.Dictionary;
import com.casecollection.backend.util.Pagination;

/**
 * Created by luqq on 16/5/8.
 */
public class DictionaryVo extends Dictionary {

    private Integer dataLevel;

    private Pagination<DictionaryVo> pagination;

    public Pagination<DictionaryVo> getPagination() {
        return pagination;
    }

    public void setPagination(Pagination<DictionaryVo> pagination) {
        this.pagination = pagination;
    }

    public Integer getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(Integer dataLevel) {
        this.dataLevel = dataLevel;
    }
}
