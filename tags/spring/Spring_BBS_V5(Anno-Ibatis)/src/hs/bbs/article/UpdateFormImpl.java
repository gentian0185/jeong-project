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
public class UpdateFormImpl {
	private String TAG = "UpdateFormImpl";
	BoardVO article;
	String pageNum;
	private int article_num;

	@Autowired
	BBSDaoService bbsDaoService;

	@RequestMapping("/updateForm.hs")
	public ModelAndView hansung(HttpServletRequest req) {
		// TODO Auto-generated method stub
		DEFINE.printTAG(TAG, " : hansung");

		article_num = Integer.parseInt(req.getParameter("article_num"));
		pageNum = req.getParameter("pageNum");

		// 한글자 출력.
		article = bbsDaoService.Content(article_num);

		System.out.println(pageNum);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNum", pageNum);
		mav.addObject("article", article);
		mav.setViewName("updateForm");
		return mav;
	}
}
