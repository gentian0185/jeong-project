package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateImpl {
	private final String TAG = "UpdateImpl";

	@RequestMapping("/update.hs")
	public ModelAndView hansung(HttpServletRequest req, BoardVO article)
			throws ServletException, IOException {

		DEFINE.printTAG(TAG, " : hansung");

//
//		int article_num = Integer.parseInt(req.getParameter("article_num"));
//		String pageNum = req.getParameter("pageNum");
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		String file_name = req.getParameter("file_name");

		try {
			BBSDao bbs_dao = BBSDao.getInstance();
			bbs_dao.Update(article.getArticle_num(), article.getTitle(), article.getContent(), article.getFile_name());
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/list.hs?pageNum="+req.getParameter("pageNum"));

		return mav;
	}
}
