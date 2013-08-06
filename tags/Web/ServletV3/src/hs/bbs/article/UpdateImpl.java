package hs.bbs.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;

public class UpdateImpl implements BBSService {
	private final String TAG = "Update ContentImpl";
	
	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DEFINE.printTAG(TAG, " : hansung");
		
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
		
		
		int article_num = Integer.parseInt(req.getParameter("article_num"));
		String pageNum = req.getParameter("pageNum");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String file_name = req.getParameter("file_name");

		try {
			BBSDao bbs_dao = BBSDao.getInstance();
			bbs_dao.Update(article_num, title, content, file_name);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}

		return "/content.hs?pageNum="+pageNum+"&article_num="+article_num; 
	}
}
