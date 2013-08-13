package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDaoService;
import hs.bbs.dao.BoardVO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WriteImpl {
	private String TAG = "WriteImpl";

	BBSDaoService bbsDaoService;

	public WriteImpl(BBSDaoService bbsDaoService) {
		this.bbsDaoService = bbsDaoService;
	}

	/*
	 * BoardVO 객체에 자동으로 저장된다. getParameter를 할 필요가 없다!!
	 * 
	 * @ModelAttribute 가 메소드위에 자동 생성되기 때문. (파라미터와 이름이 같으면 인자로 바로 넘길 수 있다.)
	 */
	@RequestMapping("/write.hs")
	public ModelAndView hansung(HttpServletRequest req, BoardVO article)
			throws ServletException, IOException {
		DEFINE.printTAG(TAG, " : hansung");

	
	
		DEFINE.printTAG(TAG, "pageNum is " + req.getParameter("pageNum"));
		try {
			bbsDaoService.Write(article);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/list.hs");
		return mav;
	}
}
