package hs.bbs.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;

/* 로그인이 되어있으면 글작성창으로
 * 안 되있으면 로그인창으로 이동시켜주는 클래스.
 */
public class WriterFormImpl implements BBSService{
	private String TAG = "WriteFormImpl";

	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		DEFINE.printTAG(TAG, " : hansung");

		String url = null;
		
		try {
			if (req.getSession().getAttribute("id") == null) {
				url = "/login.jsp";
			} else {
				url = "/writeForm.jsp";
			}
		} catch (Exception e) {
			// TODO: handle exception
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
		return url;
	}
}
