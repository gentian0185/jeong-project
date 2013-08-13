package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDaoService;
import hs.bbs.dao.BoardVO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//article_num과 pageNum을 불러들어올 수 있다.
// 게시글을 클릭 했을때의 이벤트이다.
//  BoardVO를 불러와서 content 객체에 넣어준다.
@Controller
public class ContentImpl  {
	private String TAG = "ContentImpl";
	
	private int article_num;
	private String pageNum;
	private BoardVO oneArticle;
	
	BBSDaoService bbsDaoService;
	public void setDataSource(BBSDaoService bbsDaoService){
		this.bbsDaoService=bbsDaoService;
	}
	
	@RequestMapping("/content.hs")
	public ModelAndView hansung(HttpServletRequest req){
		DEFINE.printTAG(TAG, " : hansung");
		
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");	
			article_num = Integer.parseInt(req.getParameter("article_num"));
			pageNum = req.getParameter("pageNum");
			oneArticle = bbsDaoService.Content(article_num);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		System.out.println(pageNum+"content");
		ModelAndView mav = new ModelAndView();
		mav.addObject("article_num",Integer.toString(article_num));
		mav.addObject("pageNum",pageNum);
		mav.addObject("oneArticle",oneArticle);
		mav.setViewName("content");
		
		return mav;
	}
}
