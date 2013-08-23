package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDaoService;
import hs.bbs.dao.BoardVO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReplyImpl {
	private String TAG = "ReplyImpl";

	@Autowired
	BBSDaoService bbsDaoService;
	
		@RequestMapping("/reply.hs")
	public ModelAndView hansung(HttpServletRequest req, BoardVO article) {
		DEFINE.printTAG(TAG, " : hansung");
		
		
		
		//여기서 깊이를  지정핤 ㅜ있지만 오류가 생길수 있어서 예외.
		bbsDaoService.Reply(article);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/list.hs?pageNum="+req.getParameter("pageNum"));
		return mav;
	}
}
