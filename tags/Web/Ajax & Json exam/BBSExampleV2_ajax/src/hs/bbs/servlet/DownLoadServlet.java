package hs.bbs.servlet;

import hs.bbs.DEFINE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet (urlPatterns="/download")
public class DownLoadServlet extends HttpServlet{
	private static final long serialVersionUID = -4087058198067522213L;
	
	private static String TAG = "DownLoadServlet";
	
	
	
	//get 방식과 post 방식.  둘다 ServletTest를 실행하도록 함. req 와 resp 인자로 가짐.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletTest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DEFINE.printTAG(TAG, "start");
		ServletTest(req, resp);
	}

	public void ServletTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		DEFINE.printTAG(TAG, "start");
		
		String fileName = null;
		String realFolder = "d:/temp/";
		File file = new File(realFolder+req.getParameter("file_name"));
		resp.setContentType("application/download");
		resp.setContentLength((int) file.length());

		System.out.println(file.getName());
		
		//fileName 구하기 
		if(DEFINE.isIE(req)){
			fileName = URLEncoder.encode(file.getName(), "utf-8").replace("+", "%20");
			System.out.println("익스플로러");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"),
					"iso-8859-1").replace("+","%20");//한줄
			System.out.println("익스플로러 아닌 브라우저");
		}

		resp.setHeader("Content-Disposition", "attachment;" + " filename=\"" + fileName + "\";"); // 한줄
		OutputStream out = resp.getOutputStream();
		FileInputStream fis = null;
		try {
			int temp;
			fis = new FileInputStream(file);
			while ((temp = fis.read()) != -1) {
				out.write(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {

				}
			}
		}
		
	}
}
