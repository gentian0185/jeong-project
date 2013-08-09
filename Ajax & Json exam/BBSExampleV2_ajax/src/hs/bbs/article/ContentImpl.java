package hs.bbs.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;

//article_num과 pageNum을 불러들어올 수 있다.
// 게시글을 클릭 했을때의 이벤트이다.
//  BoardVO를 불러와서 content 객체에 넣어준다.
public class ContentImpl implements BBSService {
	private String TAG = "ContentImpl";
	
	private int article_num;
	private String pageNum;
	private BoardVO oneArticle;
	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp){
		DEFINE.printTAG(TAG, " : hansung");
	
		String url = null;
		
		// TODO Auto-generated method stub
		try {

			req.setCharacterEncoding("utf-8");	
			article_num = Integer.parseInt(req.getParameter("article_num"));
			pageNum = req.getParameter("pageNum");
			BBSDao bbsDao = BBSDao.getInstance();
			oneArticle = bbsDao.Content(article_num);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		req.setAttribute("article_num", article_num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("oneArticle", oneArticle);
		
		url = "content.jsp";
		
		return url;
	}
}
