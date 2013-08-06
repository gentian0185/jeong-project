package hs.bbs.article;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;

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
		PrintHeader(req);

		req.setCharacterEncoding("utf-8");
		BoardVO article = new BoardVO();
		

		// <form action="/BBSExample/write.hs" method="post"
		// enctype="multipart.">의 경우 아래 소스는 안된다.
		// part로 받아오기 때문. getPart를 사용해야 한다.
		//article.setId(req.getParameter("id"));
		//article.setTitle(req.getParameter("title"));
		//article.setContent(req.getParameter("content"));

		// readParameterValue를 사용하는 이유 : 그냥 써도 되지만 한글이 들어가면 깨진다.
		article.setId(readParameterValue(req.getPart("id")));
		article.setTitle(readParameterValue(req.getPart("title")));
		article.setContent(readParameterValue(req.getPart("content")));

		
		

	    String realFolder="c:/upload/";	
		
	    Part filePart = req.getPart("file_name");
	    String fileName = getFileName(filePart);
	    
	    System.out.println("fileNmae  :" + fileName+"aa");

		// 화일 업로드가 없으면 화일이 저장될 디렉토리를
		// 화일로 만들려고 해서 예외발생
		if (fileName != null && !fileName.equals("")) {
			
			filePart.write(fileName);
			
			//String realPath = FileSaveHelper.save(realFolder + fileName, filePart.getInputStream());
		}
		
		
		
		
		try {
			BBSDao bbsDao = BBSDao.getInstance();
			bbsDao.Write(article);
		} catch (Exception e) {
			DEFINE.printTAG(TAG, e.toString());
			e.printStackTrace();
		}

		return "/list.hs";
	}

	//filePart로부터 파일 이름을 구하는 메소드. 
	private String getFileName(Part filePart) throws UnsupportedEncodingException {
		for (String cd : filePart.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

	//
	private String readParameterValue(Part part) throws IOException {
		InputStreamReader reader = new InputStreamReader(part.getInputStream(),
				"euc-kr");
		int temp = -1;
		StringBuilder builder = new StringBuilder();
		while ((temp = reader.read()) != -1) {
			// char로 형변환해야 문자로됨
			builder.append((char) temp);
		}
		return builder.toString();
	}

	private void PrintHeader(HttpServletRequest req) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		Collection<Part> cp = req.getParts();
		Iterator<Part> it = cp.iterator();
		int i = 1;
		while (it.hasNext()) {
			Part pa = it.next();
			Collection<String> co = pa.getHeaderNames();
			Iterator<String> itt = co.iterator();
			while (itt.hasNext()) {
				String name = itt.next();
				System.out.println(i + "번째 헤더이름 " + name);
				System.out.println(i + "번쨰헤더 값 " + pa.getHeader(name));
			}
			i++;
		}
	}
}
