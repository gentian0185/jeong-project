package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateFormImpl {
	private String TAG = "UpdateFormImpl";
	BoardVO article;
	String pageNum;
	private int article_num;

	@RequestMapping("/updateForm.hs")
	public ModelAndView hansung(HttpServletRequest req) {
		// TODO Auto-generated method stub
		DEFINE.printTAG(TAG, " : hansung");


		// TODO Auto-generated method stub
		try {

			req.setCharacterEncoding("utf-8");
			article_num = Integer.parseInt(req.getParameter("article_num"));
			pageNum = req.getParameter("pageNum");

			BBSDao bbsDao = BBSDao.getInstance();

			// 한글자 출력.
			article = bbsDao.getArticle(article_num);

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
