package hs.bbs.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;

public class DeleteImpl implements BBSService{
	private final String TAG = "DeleteImpl";	
	int article_num;
	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//BoardVO article = (BoardVO) req.getSession().getAttribute("article");
		//article_num = article.getArticle_num();
		
		article_num = Integer.parseInt(req.getParameter("article_num"));
		req.setAttribute("article_num", article_num);
		
		try {
			BBSDao bbsDao = BBSDao.getInstance();
			bbsDao.Delete(article_num);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		return "list.hs";
	}
}
