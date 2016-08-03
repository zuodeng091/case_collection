package com.casecollection.backend.service.impl;

import com.casecollection.backend.dao.DictionaryMapper;
import com.casecollection.backend.model.Dictionary;
import com.casecollection.backend.model.vo.DictionaryVo;
import com.casecollection.backend.service.DictionaryService;
import com.casecollection.backend.util.Pagination;
import com.casecollection.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Override
	public List<Dictionary> getDictionaryList(int type) {
		// TODO Auto-generated method stub
		return dictionaryMapper.getDictionaryList(type);
	}

    @Override
    public List<Dictionary> getListByTypeAndScode(Dictionary dic) {
        return dictionaryMapper.getListByTypeAndScode(dic);
    }

	@Override
	public Pagination<DictionaryVo> findDicPage(DictionaryVo dictionaryVo) {
		Pagination<DictionaryVo> pagination = dictionaryVo.getPagination();
		try {
			Integer count = dictionaryMapper.findCountByVo(dictionaryVo);
			if(count > 0){
				List<DictionaryVo> list = dictionaryMapper.findByPage(dictionaryVo);
				pagination.setData(list);
				pagination.setTotalRows(count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagination;
	}

	@Override
	public Dictionary getDictionaryById(Long id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public Response<Object> save(Dictionary dictionary) {
		Response<Object> response = new Response<Object>();
		try {
			if(dictionary == null) {
				throw new RuntimeException("参数错误");
			}
			List<Dictionary> dictionarys = dictionaryMapper.findDicByTypeAndName(dictionary);
			if(dictionary.getId() == null) {
				if(dictionarys.size() > 0){
					response.setRetCode(1);//字典已存在
					response.setMessage("该字典已存在");
				}else{
					dictionaryMapper.insertSelective(dictionary);
				}
			}else {
				if((dictionarys.size() > 0 && dictionarys.get(0).getId() == dictionary.getId())
						|| dictionarys.size() == 0){
					dictionaryMapper.updateByPrimaryKeySelective(dictionary);
				}else{
					response.setRetCode(1);//字典已存在
					response.setMessage("该字典已存在");
				}

			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			response.setRetCode(9009);
			response.setMessage("操作失败，请联系运营人员");
		}
		return  response;
	}

	@Override
	public Response<Object> delete(Long id) {
		Response<Object> response = new Response<Object>();
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setId(id);
			dictionary.setIsDelete(1);
			dictionaryMapper.updateByPrimaryKeySelective(dictionary);
		} catch (Exception e) {
			e.printStackTrace();
			response.setRetCode(9009);
			response.setMessage("操作失败，请联系运营人员");
		}

		return response;
	}
}
