<%@page contentType="text/xml; charset=utf-8"%>
<%@page import="javax.xml.transform.dom.DOMSource"%>
<%@page import="javax.xml.transform.stream.StreamResult"%>
<%@page import="javax.xml.transform.*"%>
<%@page import="dom.*"%>
<%@page import="java.io.*"%>
<%
	//테이블에 한글 데이터가있으면 "text/xml;charset=utf-8"로해야함
	//dom을 읽어들이는 방법. 받은 Dom소스를 XML로 변환하여 준다.
	response.setContentType("text/xml");
	Get_Transform gx = new Get_Transform();
	DOMSource domsource = gx.getXml();
	TransformerFactory tFactory = TransformerFactory.newInstance();
	Transformer transformer = tFactory.newTransformer();
	PrintWriter pw = response.getWriter();
	StreamResult streamResult = new StreamResult(pw);
	transformer.transform(domsource, streamResult);
	pw.println();
	pw.close();
%>
