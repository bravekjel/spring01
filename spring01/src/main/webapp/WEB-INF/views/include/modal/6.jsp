<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%
      String snames = request.getParameter("snames");
      %>
      <span><%=snames %></span>
	<response> <header>
		<resultCode> </resultCode>
		<resultMsg> </resultMsg>
	</header>
	<body>
		<items> <item> <engSecnNm> </engSecnNm>
		<isin> </isin> 
		<issuDt> </issuDt> 
		<issucoCustno> </issucoCustno>
		<korSecnNm> </korSecnNm> <secnKacdNm> </secnKacdNm> <shotnIsin> </shotnIsin>
		</item> </items>
		<numOfRows> </numOfRows>
		<pageNo> </pageNo>
		<totalCount> </totalCount>
	</body>
	</response>
</body>
</html>