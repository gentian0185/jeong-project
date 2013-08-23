package hs.bbs.article;

import java.io.File;
import java.io.IOException;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDaoService;
import hs.bbs.dao.BoardVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WriteImpl {
	private String TAG = "WriteImpl";

	@Autowired
	BBSDaoService bbsDaoService;

	@RequestMapping("/write.hs")
	public ModelAndView hansung(HttpServletRequest req, HttpSession session, BoardVO article) throws IllegalStateException, IOException {
		DEFINE.printTAG(TAG, " session 'id' is :  "+ session.getAttribute("id"));
		
		MultipartFile mtf=article.getSpringfile_name();
		if(!mtf.getOriginalFilename().equals("")){
			String realFolder="d:/upload/";
			mtf.transferTo(new File(realFolder+ mtf.getOriginalFilename()));
		}	
		
		bbsDaoService.Write(article);

		System.out.println(session.getAttribute("id")+"1111111");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/list.hs");
		return mav;
	}
}
