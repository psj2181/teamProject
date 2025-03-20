package travel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import travel.model.TravelBean;
import travel.model.TravelDao;
import utility.Paging;

@Controller
public class TravelListController {
	
	private final String command = "/list.tv";
	private final String getPage = "travelList"; // web-inf/travel/travelList.jsp
	
	@Autowired
	private TravelDao travelDao;
	
	// start.jsp(x), travelList.jsp(whatColumn,keyword)
	@RequestMapping(value = command)
	public ModelAndView doAction(HttpServletRequest request,
								 @RequestParam(value = "whatColumn",required = false) String whatColumn,
								 @RequestParam(value = "keyword",required = false) String keyword,
								 @RequestParam(value = "pageNumber",required = false) String pageNumber) {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		
		System.out.println("pageNumber : " + pageNumber);
		
		int totalCount = travelDao.getTotalCount(map); // 전체 레코드 갯수 구하기
		System.out.println("totalCount : " + totalCount);
		
		String url = request.getContextPath() + command;
		System.out.println("url : " + url); // /ex/list.tv
		// http://localhost:8080/ex/list.tv
		
		Paging pageInfo = new Paging(pageNumber,"2",totalCount,url,whatColumn,keyword);
		System.out.println("offset : " + pageInfo.getOffset());
		System.out.println("limit : " + pageInfo.getLimit());
		
		List<TravelBean> lists = travelDao.getTravelList(pageInfo,map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("travelLists",lists);
		mav.addObject("totalCount",totalCount);
		mav.addObject("pageInfo",pageInfo);
		
		mav.setViewName(getPage);
		
		return mav;
		// kim 추가함
		// master가 수정함
		// master가 수정함
	}
	
	
	
}
