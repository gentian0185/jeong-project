<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>	
<body>
<%		
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		Connection con =DriverManager.getConnection(url,"bbs","1234");	
		String sqlStr = "SELECT * FROM user_table";
		PreparedStatement pstmt = con.prepareStatement(sqlStr);
		ResultSet rs = pstmt.executeQuery();			
		 String temp="<table border='1'>";
		while(rs.next()){				
			temp=temp+"<tr>";
			temp=temp+"<td>"+rs.getString("id")+"</td>";
			temp=temp+"<td>"+rs.getString("pass")+"</td>";
			temp=temp+"</tr>";							
		}
		temp=temp+"</table>";	
		//reposne�� ��Ʈ���� �� request��ü�� ����
		PrintWriter pw=response.getWriter();	
		pw.println(temp);
		pw.close();				
		rs.close();
		pstmt.close();
		con.close();
	}catch(Exception e){
		e.printStackTrace();
	}			

%>
         
  
</body>
</html>