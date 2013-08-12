package hs.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BBSService {

	// 요청에 따라서 BBSService가 동작을 한다.
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
}
