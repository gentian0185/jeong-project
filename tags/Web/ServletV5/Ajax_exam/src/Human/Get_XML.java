package Human;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Get_XML {	
    Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	StringBuffer sb;
		
	public StringBuffer getXml(){
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:human";
		con =DriverManager.getConnection(url,"human","1234");		
	
		String sqlStr = "SELECT * FROM login";
		pstmt = con.prepareStatement(sqlStr);
		ResultSet rs = pstmt.executeQuery();
		sb=new StringBuffer();
		sb.append("<?xml version='1.0' encoding='utf-8' ?>\n");
		sb.append("<human>\n");
				
		while(rs.next()){			
			sb.append("<Member>\n");
			sb.append("<id>"+rs.getString("id")+"</id>\n");
			sb.append("<pass>"+rs.getString("pass")+"</pass>\n");
			sb.append("</Member>\n");
		}
		sb.append("</human>");				
		rs.close();
		pstmt.close();
		con.close();		
	} catch(Exception e) {
		e.printStackTrace();
	} 
	return sb;
   }
}




