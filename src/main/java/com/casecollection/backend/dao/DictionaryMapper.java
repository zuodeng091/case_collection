package com.casecollection.backend.dao;

import com.casecollection.backend.model.Dictionary;
import com.casecollection.backend.model.vo.DictionaryVo;

import java.util.List;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
    List<Dictionary> getDictionaryList(int type);
    
    List<Dictionary> getListByTypeAndScode(Dictionary dic);

    List<DictionaryVo> findByPage(DictionaryVo dictionaryVo);

    Integer findCountByVo(DictionaryVo dictionaryVo);

    List<Dictionary> findDicByTypeAndName(Dictionary dictionary);
}