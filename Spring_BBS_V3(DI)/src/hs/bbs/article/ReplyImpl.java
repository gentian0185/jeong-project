package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDaoService;
import hs.bbs.dao.BoardVO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReplyImpl {
	private String TAG = "ReplyImpl";

	BBSDaoService bbsDaoService;
	public ReplyImpl(BBSDaoService bbsDaoService){
		this.bbsDaoService=bbsDaoService;
	}
		@RequestMapping("/reply.hs")
	public ModelAndView hansung(HttpServletRequest req, BoardVO article)
			throws Exception {
		DEFINE.printTAG(TAG, " : hansung");

		bbsDaoService.Reply(article);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/list.hs?pageNum="+req.getParameter("pageNum"));
		return mav;
	}
}
