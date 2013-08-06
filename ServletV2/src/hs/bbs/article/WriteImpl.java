package hs.bbs.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;

public class WriteImpl implements BBSService {
	private String TAG = "WriteImpl";
	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DEFINE.printTAG(TAG, " : hansung");
		
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		BoardVO article = new BoardVO();
		
		article.setId(req.getParameter("id"));
		article.setTitle(req.getParameter("title"));
		article.setContent(req.getParameter("content"));

		try {
			BBSDao bbsDao = BBSDao.getInstance();
			bbsDao.Write(article);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		
		return "/list.hs";
	}
}
