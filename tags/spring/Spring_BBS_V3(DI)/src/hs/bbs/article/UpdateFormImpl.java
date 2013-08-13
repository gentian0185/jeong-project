package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDaoService;
import hs.bbs.dao.BoardVO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateFormImpl {
	private String TAG = "UpdateFormImpl";
	BoardVO article;
	String pageNum;
	private int article_num;

	BBSDaoService bbsDaoService;
	public UpdateFormImpl(BBSDaoService bbsDaoService){
		this.bbsDaoService=bbsDaoService;
	}
	
	@RequestMapping("/updateForm.hs")
	public ModelAndView hansung(HttpServletRequest req) {
		// TODO Auto-generated method stub
		DEFINE.printTAG(TAG, " : hansung");

		try {

			req.setCharacterEncoding("utf-8");
			article_num = Integer.parseInt(req.getParameter("article_num"));
			pageNum = req.getParameter("pageNum");

			// 한글자 출력.
			article = bbsDaoService.getArticle(article_num);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		System.out.println(pageNum);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNum", pageNum);
		mav.addObject("article", article);
		mav.setViewName("updateForm");
		return mav;
	}
}
