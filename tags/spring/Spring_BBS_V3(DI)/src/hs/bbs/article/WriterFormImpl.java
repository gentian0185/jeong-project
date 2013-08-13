package hs.bbs.article;

import hs.bbs.DEFINE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/* 로그인이 되어있으면 글작성창으로
 * 안 되있으면 로그인창으로 이동시켜주는 클래스.
 */

@Controller
public class WriterFormImpl {
	private String TAG = "WriteFormImpl";

	@RequestMapping("/writeForm.hs")
	public ModelAndView hansung(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		DEFINE.printTAG(TAG, " : hansung");

		ModelAndView mav = new ModelAndView();
		try {
			if (req.getSession().getAttribute("id") == null) {
				mav.setViewName("login");
			} else {
				mav.setViewName("writeForm");
			}
		} catch (Exception e) {
			// TODO: handle exception
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		return mav;
	}
}
