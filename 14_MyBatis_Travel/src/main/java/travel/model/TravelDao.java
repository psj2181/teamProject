package travel.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import utility.Paging;

//@Component("myTravelDao")
@Service("myTravelDao")
public class TravelDao {
	
	private String namespace = "travel.TravelBean";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public TravelDao() {
		System.out.println("TravelDao()");
	}//TravelDao

	public List<TravelBean> getTravelList(Paging pageInfo, Map<String, String> map) {
		String wc = map.get("whatColumn");
		String kw = map.get("keyword");
		
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		/*
		p1 : new RowBounds(0,2);
		p2 : new RowBounds(2,2);
		p3 : new RowBounds(4,2);
		*/
		List<TravelBean> lists = sqlSessionTemplate.selectList(namespace + ".getTravelList",map,rowBounds);
		System.out.println("lists.size() : " + lists.size());
		return lists;
		
	}//getTravelList

	public int insertTravel(TravelBean travel) {
		
		int cnt = sqlSessionTemplate.insert(namespace + ".insertTravel",travel);
		System.out.println("insertTravel cnt : " + cnt);
		return cnt;
		
	}//insertTravel

	public int deleteTravel(int num) {
		
		int cnt = sqlSessionTemplate.delete(namespace + ".deleteTravel",num);
		System.out.println("deleteTravel cnt : " + cnt);
		return cnt;
	}//deleteTravel

	public TravelBean getOneSelect(int num) {
		
		TravelBean travel = sqlSessionTemplate.selectOne(namespace + ".getOneSelect",num);
		
		return travel;
	}

	public int updateTravel(TravelBean travel) {
		
		int cnt = sqlSessionTemplate.update(namespace + ".updateTravel",travel);
		
		return cnt;
	}

	public int getTotalCount(Map<String, String> map) {
		
		int cnt = sqlSessionTemplate.selectOne(namespace + ".getTotalCount",map);
		System.out.println("getTotalCount cnt : " + cnt);
		return cnt;
	}
	
}
