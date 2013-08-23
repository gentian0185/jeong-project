package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDaoService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeleteImpl {
	private final String TAG = "DeleteImpl";
	int article_num;

	@Autowired
	BBSDaoService bbsDaoService;

	@RequestMapping("/deleteForm.hs")
	public String hansung(HttpServletRequest req) throws ServletException,
			IOException {
		DEFINE.printTAG(TAG, " : hansung");

		article_num = Integer.parseInt(req.getParameter("article_num"));
		String pageNum = req.getParameter("pageNum");

		System.out.println(pageNum + " delete");

		bbsDaoService.Delete(article_num);

		return "redirect:/list.hs?pageNum=" + pageNum;
	}
}
