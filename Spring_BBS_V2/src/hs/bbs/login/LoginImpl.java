package hs.bbs.login;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


		
@Controller
public class LoginImpl {
	private String TAG = "LoginImpl";

	@RequestMapping("/login.hs")
	public ModelAndView hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//사용자가 입력하는 값.
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String msg = "";
		
		//db_pass는 DB의 id에 대한 패스워드  .  id가 없으면 null
		String db_pass = null;
		
		//id에 대한 pass를 뽑아내는 것을 요청.
		try {
			BBSDao bbsDao = BBSDao.getInstance();
			db_pass = bbsDao.getPass(id);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		System.out.println(db_pass);


		ModelAndView mav = new ModelAndView();
		//만약 db_pass가 없으면 회원가입 유도. db_pass가 안맞으면 로그인화면
		if(db_pass ==null){
			mav.setViewName("memberJoin");
		}
		else if(db_pass.equals(pass)){
			//id를 세션에 넣는다.;
			req.getSession().setAttribute("id", id);
			mav.setViewName("writeForm");
		}
		//패스워드 틀림.
		else {
			msg="패스워드 틀림";
			mav.addObject("wrong_pass",msg);
			mav.setViewName("login");
		}
		return mav;
	}
}
