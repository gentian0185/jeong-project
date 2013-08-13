package hs.bbs.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;


@Controller
public class DeleteImpl {
	private final String TAG = "DeleteImpl";	
	int article_num;
	
	@RequestMapping("/deleteForm.hs")
	public String hansung(HttpServletRequest req)
			throws ServletException, IOException {
		
		article_num = Integer.parseInt(req.getParameter("article_num"));
		String pageNum=req.getParameter("pageNum");
		
		System.out.println(pageNum+" delete");
		
		try {
			BBSDao bbsDao = BBSDao.getInstance();
			bbsDao.Delete(article_num);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		return "redirect:/list.hs?pageNum="+pageNum;
	}
}
