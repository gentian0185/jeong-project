package hs.bbs.login;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDaoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("id")
public class LoginImpl implements HttpSessionListener{
	private String TAG = "LoginImpl";

	@Autowired
	BBSDaoService bbsDaoService;
	
	@RequestMapping("/login.hs")
	public ModelAndView hansung( HttpServletRequest req) {
		
		//사용자가 입력하는 값.
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String db_pass = bbsDaoService.getPass(id).get(0);

		DEFINE.printTAG(TAG, db_pass);
		
		ModelAndView mav = new ModelAndView();
		//id에 대한 pass를 뽑아내는 것을 요청.
		//db_pass = bbsDaoService.getPass(id);
		if(bbsDaoService.getPass(id).size()==0){
			mav.setViewName("memberJoin");
		}
		else if(pass.equals(db_pass)){
			//id를 세션에 넣는다.;
			//req.getSession().setAttribute("id", id);

			req.getSession().setMaxInactiveInterval(20*60);
			mav.addObject("id",id );
			mav.setViewName("writeForm");
			System.out.println("ddddddddddddddddd    "+req.getSession().getAttribute("id"));
			return mav;
		}
		//패스워드 틀림.
		else {
			String msg="패스워드 틀림";
			mav.addObject("wrong_pass",msg);
			mav.setViewName("login");
		}

		return mav;
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		DEFINE.printTAG(TAG, session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		DEFINE.printTAG(TAG, session.getId()+" is destroy");
	}
}
