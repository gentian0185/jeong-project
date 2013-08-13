package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReplyImpl {
	private String TAG = "ReplyImpl";

	// article 값 그대로 인자로 받는다. (@ModelAttribute )
	// article 의 변수들의 이름이 파라메터값과 같음.
	@RequestMapping("/reply.hs")
	public ModelAndView hansung(HttpServletRequest req, BoardVO article)
			throws Exception {
		DEFINE.printTAG(TAG, " : hansung");

		// 인자로 값이 들어오기 때문에 필요없다.
		// BoardVO article = new BoardVO();
		// article.setId(req.getParameter("id"));
		// article.setDepth(Integer.parseInt(req.getParameter("depth")));
		// article.setGroup_id(Integer.parseInt(req.getParameter("group_id")));
		// article.setPos(Integer.parseInt(req.getParameter("pos")));
		//
		// article.setTitle(req.getParameter("title"));
		// article.setContent(req.getParameter("content"));
		// article.setFile_name(req.getParameter("file_name"));

		BBSDao bbsDao = BBSDao.getInstance();
		bbsDao.Reply(article);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/list.hs?pageNum="+req.getParameter("pageNum"));
		return mav;
	}
}
