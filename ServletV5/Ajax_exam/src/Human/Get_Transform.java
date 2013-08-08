package Human;

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
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:human";
		con =DriverManager.getConnection(url,"human","1234");		
	
		String sqlStr = "SELECT * FROM login";
		pstmt = con.prepareStatement(sqlStr);
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData rsMeta = rs.getMetaData();
		int columnCount = rsMeta.getColumnCount();	
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();		
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document document = dBuilder.newDocument();		
		Element rootElement = document.createElement("XMLTEST");
		document.appendChild(rootElement);
		
		//테이블 안에 null값이 있으면 result2.jsp에서 변환시 예외발생함
		while(rs.next()){			
			Element memberElement = document.createElement("Member");
			rootElement.appendChild(memberElement);			
			for(int i = 1 ; i <= columnCount ; i++) {
				Element element = document.createElement(rsMeta.getColumnName(i));
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
