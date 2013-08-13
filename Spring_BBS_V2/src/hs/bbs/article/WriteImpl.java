package hs.bbs.article;

import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;
import hs.bbs.file.FileSaveHelper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WriteImpl {
	private String TAG = "WriteImpl";

	/*  BoardVO 객체에 자동으로 저장된다. getParameter를 할 필요가 없다!!
	 *   @ModelAttribute 가 메소드위에 자동 생성되기 때문.
	 *  (파라미터와 이름이 같으면 인자로 바로 넘길 수 있다.)*/
	@RequestMapping("/write.hs")
	public ModelAndView hansung(HttpServletRequest req, BoardVO article)
			throws ServletException, IOException {
		DEFINE.printTAG(TAG, " : hansung");

		// 헤더 정보 출력
		DEFINE.printHeader(req);


		// String realFolder = "D:\\temp\\";
		// Part filePart = req.getPart("file_name");
		// String fileName = getFileName(filePart);
		//
		// DEFINE.printTAG(TAG, "fileName : " + fileName);
		//
		// // 업로드할 파일이 없으면 파일이 저장될 디렉토리를 파일로 만들려고 하는 아주 큰 불상사가 생긴다.
		// if (fileName != null && !fileName.equals("")) {
		//
		// // filePart.write(fileName);
		// String realPath = FileSaveHelper.save(realFolder + fileName,
		// filePart.getInputStream());
		// }

		// article.setFile_name(fileName);
		
		try {
			BBSDao bbsDao = BBSDao.getInstance();
			bbsDao.Write(article);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/list.hs?pageNum=="+req.getParameter("pageNum"));
		return mav;
	}

	// filePart로부터 파일 이름을 구하는 메소드.
	// content-disposition 헤더에 대한 form-data; name="file_name"; filename="ee.txt"
	// 중 filename에 대한 값만을 구해내는 메소드.
	private String getFileName(Part filePart)
			throws UnsupportedEncodingException {
		for (String cd : filePart.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				// = 다음에 나오는 문자들을 자르는데 공백을 없애며 ( 공백은 +로 처리되기 때문) "를 없앤다.

				cd = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				// cd = DEFINE.removePath(cd);

				return cd;
			}
		}
		return null;
	}

	
}
