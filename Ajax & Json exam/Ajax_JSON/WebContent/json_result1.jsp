<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%		

	// 억지로 해쉬맵에 어레이 리스트를 사용하여 만든 모든 아이디 출력.
	// 이 방법 보다는 JsonArray로 만드는게 바람직하다
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		Connection con =DriverManager.getConnection(url,"bbs","1234");		
	
		String sqlStr = "SELECT id FROM user_table";
		PreparedStatement pstmt = con.prepareStatement(sqlStr);
		ResultSet rs = pstmt.executeQuery();			
		ArrayList<String> ids = new ArrayList<String>();		
		while(rs.next()){
			ids.add(rs.getString(1));	
		}
		HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
	
		//해쉬맵에 arraylist를 넣음.
		hm.put("ids",ids);
		JSONObject jb= new JSONObject(hm);
		PrintWriter pw=response.getWriter();	
		pw.println(jb);
		//스트림닫아야함/	
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