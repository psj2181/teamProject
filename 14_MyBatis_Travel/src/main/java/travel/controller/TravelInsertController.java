package travel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import travel.model.TravelBean;
import travel.model.TravelDao;

@Controller
public class TravelInsertController {
	
	private final String command = "insert.tv";
	private final String getPage = "TravelInsertForm";
	private final String gotoPage = "redirect:/list.tv";
	
	@Autowired
	private TravelDao travelDao;
	
	// travelList.jsp에서 추가하기 클릭
	@RequestMapping(value = command,method = RequestMethod.GET)
	public String doActionGet() {
		return getPage;
	}
	
	@RequestMapping(value = command,method = RequestMethod.POST)
	public ModelAndView doActionPost(@ModelAttribute("travel") @Valid TravelBean travel, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		int cnt = travelDao.insertTravel(travel);
		mav.setViewName(gotoPage);
		
		return mav;
	}
	
	
	
}
