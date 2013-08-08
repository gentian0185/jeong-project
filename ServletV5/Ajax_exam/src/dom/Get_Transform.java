package dom;

import java.sql.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Get_Transform {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;	
	DOMSource domSource;
	
	public DOMSource getXml(){
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		con =DriverManager.getConnection(url,"bbs","1234");		
	
		String sqlStr = "SELECT * FROM user_table";
		pstmt = con.prepareStatement(sqlStr);
		ResultSet rs = pstmt.executeQuery();
		//컬럼 이름을 반환한다.
		ResultSetMetaData rsMeta = rs.getMetaData();
		int columnCount = rsMeta.getColumnCount();	
	
		
		/* DocumentBuilder 클래스는 팩토리메소드 패턴을 지원한다
		 * 즉 DocumentBuilderFactory 는 DocumentBuilder의 생성자를 만들어주는 클래스이다.
		 * DocumentBuilder도 Document를 생성해주는 클래스 (Factory)
		 * 
		 * Document는 Element를 지닌다.(태그)
		 * (자바에서는 element라 부르고 자바스크립트에서는 노드라 부른다.)
		 * 
		 */
		//DocumentBuilder는 자바에서 xml파일을 dom으로 읽을수 있는 클래스.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();		
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document document = dBuilder.newDocument();		
		//element 생성. 이것이 root
		Element rootElement = document.createElement("XMLTEST");
		//document의 appendChild는 인자에 대한 엘리먼트를 추가시켜줌.
		document.appendChild(rootElement);
		
		//테이블 안에 null 값이 있으면 result2.jsp에 변환시 예외 발생함.
		while(rs.next()){			
			//Element의 appdneChild메소드는 인자가 현 Element의 값으로 들어간다.
			Element memberElement = document.createElement("Member");
			rootElement.appendChild(memberElement);
			//컬럼 갯수만큼 만듬.
			for(int i = 1 ; i <= columnCount ; i++) {
				//컬럼 이름이 엘리먼트로 들어간다.
				Element element = document.createElement(rsMeta.getColumnName(i));
				//텍스트로 데이타가 들어간다.
				Text text = document.createTextNode(rs.getString(i));
				
				element.appendChild(text);
				memberElement.appendChild(element);				
			}			
		}
		rs.close();
		pstmt.close();
		con.close();
		
		domSource = new DOMSource(document);		
		
	} catch(Exception e) {
		e.printStackTrace();
	} 
	return domSource;
   }
	

}
