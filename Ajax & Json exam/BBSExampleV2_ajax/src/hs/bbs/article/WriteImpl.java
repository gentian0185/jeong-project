package hs.bbs.article;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.dao.BBSDao;
import hs.bbs.dao.BoardVO;
import hs.bbs.file.FileSaveHelper;

public class WriteImpl implements BBSService {
	private String TAG = "WriteImpl";

	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DEFINE.printTAG(TAG, " : hansung");

		// 헤더 정보 출력
		DEFINE.printHeader(req);

		req.setCharacterEncoding("utf-8");
		BoardVO article = new BoardVO();

		
		String realFolder = "D:\\temp\\";

		Part filePart = req.getPart("file_name");
		
		String fileName = getFileName(filePart);
		
		DEFINE.printTAG(TAG, "fileName : "+ fileName);	

		// 업로드할 파일이 없으면 파일이 저장될 디렉토리를 파일로 만들려고 하는 아주 큰 불상사가 생긴다.
		if (fileName != null && !fileName.equals("")) {
			
			// filePart.write(fileName);
			String realPath = FileSaveHelper.save(realFolder + fileName,
					filePart.getInputStream());
		}

		// <form action="/BBSExample/write.hs" method="post"
		// enctype="multipart.">의 경우 한글 출력이 깨진다. getPart를 사용해야 한다.
		// article.setId(req.getParameter("id"));
		// article.setTitle(req.getParameter("title"));
		// article.setContent(req.getParameter("content"));

		// readParameterValue를 사용하는 이유 : 그냥 써도 되지만 한글이 들어가면 깨진다.
		article.setId(readParameterValue(req.getPart("id")));
		article.setTitle(readParameterValue(req.getPart("title")));
		article.setContent(readParameterValue(req.getPart("content")));

		article.setFile_name(fileName);

		try {
			BBSDao bbsDao = BBSDao.getInstance();
			bbsDao.Write(article);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}

		return "/list.hs";
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
				//cd = DEFINE.removePath(cd);
				
				return cd;
			}
		}
		return null;
	}

	// part를 stream받아서 String으로 변환시켜준다. 바로 string으로 변환시키면 한글이 깨지기 때문.
	private String readParameterValue(Part part) throws IOException {
		InputStreamReader reader = new InputStreamReader(part.getInputStream(),
				"utf-8");
		int temp = -1;
		StringBuilder builder = new StringBuilder();
		while ((temp = reader.read()) != -1) {
			// char로 형변환해야 문자로됨
			builder.append((char) temp);
		}
		return builder.toString();
	}
}
