package AJAX;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextImpl implements BBSInter{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	StringBuffer sb;
	
	public void human(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/xml;charset=utf-8");		
	
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
		PrintWriter pw= response.getWriter();
		pw.println(sb.toString());
		rs.close();
		pstmt.close();
		con.close();		
	} catch(Exception e) {
		e.printStackTrace();
	} 	
   }

}