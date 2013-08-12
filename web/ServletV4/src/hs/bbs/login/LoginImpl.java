package hs.bbs.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;

public class LoginImpl implements BBSService{
	private String TAG = "LoginImpl";

	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = null;
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

		//만약 db_pass가 없으면 회원가입 유도. db_pass가 안맞으면 로그인화면
		if(db_pass ==null){
			url = "memberJoin.jsp";
		}
		else if(db_pass.equals(pass)){
			//id를 세션에 넣는다.
			req.getSession().setAttribute("id", id);
			url = "writeForm.hs";
		}
		//패스워드 틀림.
		else {
			msg="패스워드 틀림";
			req.setAttribute("wrong_pass", msg);
			url = "login.hs";
		}
		DEFINE.printTAG(TAG, url);
		
		return url;
	}
}
