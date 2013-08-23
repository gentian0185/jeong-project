package hs.bbs.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadImpl  {
	@RequestMapping("/download.hs")
	public ModelAndView hansung(HttpServletRequest req){
		ModelAndView mav = new ModelAndView();
		String realFolder = "d:/upload/";
		mav.addObject("file_name", new File(realFolder+req.getParameter("file_name")));
		//원래는 resolve view에 의해서 작동하지만 bean name view resolve 에 의해 view의 이름을 찾도록 만들었다.
		mav.setViewName("downloadview");
		return mav;
	}
}