package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import travel.model.TravelDao;

@Controller
public class TravelDeleteController {
	
	private final String command = "delete.tv";
	private final String gotoPage = "redirect:/list.tv"; /* get */
	
	@Autowired
	TravelDao travelDao;
	
	@RequestMapping(command) // get
	public String doAction(@RequestParam("num") int num) {
		
		int cnt = travelDao.deleteTravel(num);
		
		return gotoPage;
	}
	
}
