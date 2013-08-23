package hs.bbs.file;

import hs.bbs.DEFINE;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		
		String fileName = null;
		resp.setContentType("application/download");
		File file = (File) model.get("file_name");
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
