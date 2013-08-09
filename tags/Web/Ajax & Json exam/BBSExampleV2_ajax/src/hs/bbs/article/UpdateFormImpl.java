package hs.bbs.article;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormImpl implements BBSService {
	private String TAG = "UpdateFormImpl";
	BoardVO article;
	String pageNum;
	private int article_num;
	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		DEFINE.printTAG(TAG, " : hansung");
		
		String url = null;

		// TODO Auto-generated method stub
		try {

			req.setCharacterEncoding("utf-8");	
			article_num = Integer.parseInt(req.getParameter("article_num"));
			pageNum=req.getParameter("pageNum");
			
			BBSDao bbsDao = BBSDao.getInstance();
			
			//한글자 출력.
			article = bbsDao.getArticle(article_num);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("article", article);
		

		
		url = "updateForm.jsp";
		
		return url;
	}
}
