<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.*" %>
<%@ page import="Ajax.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%		
	try{
		LoginVO login=null;
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		Connection con =DriverManager.getConnection(url,"bbs","1234");		
	
		String sqlStr = "SELECT * FROM user_table where id='ruby'";
		PreparedStatement pstmt = con.prepareStatement(sqlStr);
		ResultSet rs = pstmt.executeQuery();		
		
		if(rs.next()){
			 login = new LoginVO(rs.getString(1),rs.getString(2));				
		}
		
		JSONObject jb= new JSONObject(login); 
		PrintWriter pw=response.getWriter();	
		pw.println(jb);
		//스트림안닫으면 에러가 발생	
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