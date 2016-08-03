package com.casecollection.backend.service;


import com.casecollection.backend.model.Dictionary;
import com.casecollection.backend.model.vo.DictionaryVo;
import com.casecollection.backend.util.Pagination;
import com.casecollection.common.Response;

import java.util.List;

public interface DictionaryService {
	
    List<Dictionary> getDictionaryList(int type);

    List<Dictionary> getListByTypeAndScode(Dictionary dic);

    Pagination<DictionaryVo> findDicPage(DictionaryVo dictionaryVo);

    Dictionary getDictionaryById(Long id);

    /**
     * 保存字典信息
     * @param dictionary
     */
    Response<Object> save(Dictionary dictionary);

    /**
     * 删除字典信息
     * @param id
     */
    Response<Object> delete(Long id);

}
