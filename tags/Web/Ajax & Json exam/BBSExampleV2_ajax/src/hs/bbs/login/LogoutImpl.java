package hs.bbs.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;

public class LogoutImpl implements BBSService{
	private String TAG = "LogoutImpl";

	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String url = "list.hs";
		String id =	(String ) req.getSession().getAttribute("id");
		
		DEFINE.printTAG(TAG, id+"   is logout");
		req.getSession().invalidate();
		
		return url;
	}
}
