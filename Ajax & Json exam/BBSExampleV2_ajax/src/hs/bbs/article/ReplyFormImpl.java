package hs.bbs.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;

public class ReplyFormImpl implements BBSService {
	private String TAG = "ReplyFormImpl";

	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DEFINE.printTAG(TAG, " : hansung");
		
		//hidden으로 가져와서 저장함.
		req.setAttribute("pageNum", req.getParameter("pageNum"));
		req.setAttribute("depth", req.getParameter("depth"));
		req.setAttribute("group_id", req.getParameter("group_id"));
		req.setAttribute("pos", req.getParameter("pos"));
		
		return "replyForm.jsp";
	}
}
