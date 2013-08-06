package hs.bbs.servlet;

import hs.bbs.BBSService;
import hs.bbs.DEFINE;
import hs.bbs.list.BoardListImpl;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//WebInitParam Annotation은 ServletConfig에 정보를 저장한다.
@WebServlet(urlPatterns="*.hs", initParams=@WebInitParam(name="propertiesPath", value="D:/Developer/jeong-workspace/EE/BBSExampleV2/WebContent/bbs.properties"))
public class HSServlet extends HttpServlet {
	private static final long serialVersionUID = 1948795483855381566L;
	
	private String TAG = "HSServlet";
	HashMap<String, Object> hm = new HashMap<String, Object>();
	BBSService boardList = new BoardListImpl();
	
	//초기화 : 해시맵에 명령어를 추가시킴.  값은 BBSService를 상속받는 클래스.
	@Override
	public void init(ServletConfig config) {
		//localhost:8080/TestServlet/test.test
		String propertiesPath;
		propertiesPath = config.getInitParameter("propertiesPath");
		
		MyProperties myProps = new MyProperties(propertiesPath);
		hm = myProps.getHashMap();
	}
	
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
		ServletTest(req, resp);
	}

	public void ServletTest(HttpServletRequest req, HttpServletResponse resp) {
		String command = getCommand(req, resp);
		DEFINE.printTAG(TAG, "result command : " +command);
		// 테스트 하기위해 클래스에 넣었음.
		boardList = (BBSService) hm.get(command);
		// view 는 이동할 페이지.
		String view;
		try {
			view = boardList.hansung(req, resp);

			// view 페이지로 이동함.
			if (view != null) {
				RequestDispatcher dispatcher = req.getRequestDispatcher(view);
				dispatcher.forward(req, resp);
			}
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//경로중 명령어 부분만을 반환시킴.
	private String getCommand(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String command = req.getRequestURI();
		System.out.println();
		//System.out.println("전체경로 : "+ command);
		//System.out.println("컨텍스트 경로 : "+req.getContextPath() );
		//indexOf : 해당 문자가 들어있는 위치를 알려줌. 0이 되야 정상.
		if(command.indexOf(req.getContextPath())==0){
			command=command.substring(req.getContextPath().length(), command.length());
		}
		return command;
	}
}
