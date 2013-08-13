package hs.bbs.list;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardListImpl {
	String TAG = "BoardListImpl";
	
	@RequestMapping("/list.hs")
	public ModelAndView hansung(HttpServletRequest req) {
		//언제 어디서나 request를 얻어오는 코드
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		DEFINE.printTAG(TAG, " : hansung");

		// DB 데이타 변수.
		int count = 0;
		ArrayList<BoardVO> arrayList = null;
		String pageCode = null;

		// 페이지 데이타 저장.
		int pageSize = 10;
		int pageBlock = 10;
		Page page = null;
		String pageNum = null;
	
		pageNum = req.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";

		// 싱글톤 패턴
		BBSDao bbsDao = BBSDao.getInstance();

		// db에서 값을 가져옴.
		try {
			count = bbsDao.getCount();
			// 페이지 객체 생성
			page = new Page(Integer.parseInt(pageNum), count, pageSize,
					pageBlock);
			pageCode = page.getSb().toString();
			// arrayList = bbsDao.getAllartticle();

			arrayList = bbsDao.List(page.getStartRow(), page.getEndRow());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageCode", pageCode);
		mav.addObject("pageNum", pageNum);
		mav.addObject("count", count);
		mav.addObject("article", arrayList);

		mav.setViewName("list");

		// 반환해주고 나면 view-name 에 해당하는 파일이 실행된다.
		return mav;
	}
}
