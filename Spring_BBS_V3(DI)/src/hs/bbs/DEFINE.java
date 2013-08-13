package hs.bbs;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public final class DEFINE {
	public static void printTAG(String ClassName, String msg) {
		// TODO Auto-generated method stub
		System.out.println(ClassName + "| " + msg);
	}

	// 헤더를 읽어오는 함수. 헤더를 확인해보기 위해 만들었다.
	public static void printHeader(HttpServletRequest req)
			throws ServletException, IOException {
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

	public static boolean isIE(HttpServletRequest req) throws ServletException,
			IOException {
		String userAgent = req.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;

		return ie;
	}

	public static String removePath(String pullPath) {
		Vector<String> vector = new Vector<String>();
		// url = 경로
		StringTokenizer st = new StringTokenizer(pullPath, "\\");
		while (st.hasMoreTokens()) {
			for (int i = 0; i < st.countTokens(); i++) {
				vector.addElement(st.nextToken());
			}
		}
		String filename = vector.lastElement();
		return filename;
	}
}
