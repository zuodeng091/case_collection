package com.casecollection.backend.controller;


import com.casecollection.backend.framework.bean.UserSession;
import com.casecollection.backend.model.Dictionary;
import com.casecollection.backend.model.vo.DictionaryVo;
import com.casecollection.backend.service.DictionaryService;
import com.casecollection.backend.util.Pagination;
import com.casecollection.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 字典控制类
 * @author luqq
 *
 */
@Controller
@RequestMapping(value="/dictionary")
public class DictionaryController {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping(value="/getDictionaryList")
	@ResponseBody
	public List<Dictionary> getDictionaryList(int type){
		List<Dictionary> dictionaryList = dictionaryService.getDictionaryList(type);
		return dictionaryList;
	}

	@RequestMapping(value="/toList")
	public String toDicList(Model model){
		return "/backend/dictionary/dicList";
	}

	/**
	 * 分页查询字典列表
	 * @param dictionaryVo
	 * @param pagination
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findDicPage")
	@ResponseBody
	public Pagination<DictionaryVo> findDicPage(DictionaryVo dictionaryVo, Pagination<DictionaryVo> pagination, HttpServletRequest request){
		Object sCode = request.getSession().getAttribute("sCode");
		UserSession user = (UserSession)request.getSession().getAttribute("user");
		if(user.getDataLevel() != -1 && sCode == null) {
			return null;
		}
		if(user.getDataLevel() != -1){
			dictionaryVo.setsCode(sCode.toString());
		}
		dictionaryVo.setPagination(pagination);
		dictionaryVo.setDataLevel(user.getDataLevel());

		return dictionaryService.findDicPage(dictionaryVo);

	}

	@RequestMapping(value="/toEdit")
	public String toEdit(@RequestParam(value="id", required=false)Long id, Model model, HttpServletRequest request){
		if(id != null){
			Dictionary dictionary = dictionaryService.getDictionaryById(id);
			model.addAttribute("dictionary", dictionary);
		}
		return "/backend/dictionary/dicEdit";
	}

	/**
	 * 保存字典信息
	 * @param dictionary
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Response<Object> save(Dictionary dictionary, HttpServletRequest request){
		Response<Object> response = new Response<Object>();
		String sCode = String.valueOf(request.getSession().getAttribute("sCode"));
		dictionary.setsCode(sCode);
		Object obj = request.getSession().getAttribute("user");
		UserSession user = (UserSession) obj;
		if(dictionary.getId() == null){
			dictionary.setCreator(user.getName());
			dictionary.setModifier(user.getName());
		}else{
			dictionary.setModifier(user.getName());
		}
		response = dictionaryService.save(dictionary);
		return response;
	}

	/**
	 * 删除字典信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Response<Object> delete(Long id, HttpServletRequest request){
		Response<Object> response = new Response<Object>();
		response = dictionaryService.delete(id);
		return response;
	}

}
