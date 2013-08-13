package hs.bbs.login;

import hs.bbs.DEFINE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutImpl {
	private String TAG = "LogoutImpl";

	@RequestMapping("/logout.hs")
	public ModelAndView hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = (String) req.getSession().getAttribute("id");

		DEFINE.printTAG(TAG, id + "   is logout");
		req.getSession().invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		return mav;
	}
}
