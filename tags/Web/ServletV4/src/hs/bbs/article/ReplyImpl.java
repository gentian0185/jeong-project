package hs.bbs.article;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplyImpl implements BBSService{
	private String TAG = "ReplyImpl";
	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DEFINE.printTAG(TAG, " : hansung");
		
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		BoardVO article = new BoardVO();
		
		article.setId(req.getParameter("id"));
		article.setDepth(Integer.parseInt(req.getParameter("depth")));
		article.setGroup_id(Integer.parseInt(req.getParameter("group_id")));
		article.setPos(Integer.parseInt(req.getParameter("pos")));
		
		article.setTitle(req.getParameter("title"));
		article.setContent(req.getParameter("content"));
		article.setFile_name(req.getParameter("file_name"));
		try {
			BBSDao bbsDao = BBSDao.getInstance();
			bbsDao.Reply(article);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		return "/list.hs";
	}
}
