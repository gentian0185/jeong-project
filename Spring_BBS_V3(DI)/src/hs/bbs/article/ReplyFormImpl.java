package hs.bbs.article;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hs.bbs.DEFINE;

@Controller
public class ReplyFormImpl {
	private String TAG = "ReplyFormImpl";

	@RequestMapping("/replyForm.hs")
	public ModelAndView hansung(HttpServletRequest req) {

		DEFINE.printTAG(TAG, " : hansung");
		ModelAndView mav = new ModelAndView();

		mav.addObject("pageNum",req.getParameter("pageNum"));
		mav.addObject("depth",req.getParameter("depth"));
		mav.addObject("group_id",req.getParameter("group_id"));
		mav.addObject("pos",req.getParameter("pos"));
		
		mav.setViewName("replyForm");
		return mav;
	}
}
