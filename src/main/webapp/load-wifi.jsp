<%@ page import="com.wifi.publicwifiproject.service.OpenAPIService" %><%--
  Created by IntelliJ IDEA.
  User: ksh
  Date: 2024. 8. 7.
  Time: PM 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <%
        OpenAPIService openAPIService = new OpenAPIService();
        int wifiCnt = openAPIService.loadWifiInfo();
    %>
    <div>
        <% if (wifiCnt > 0) { %>
        <div style="text-align: center">
            <h1><%=wifiCnt%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
            <a href="index.jsp">홈 으로 가기</a>
        </div>
        <% } else { %>
        <div style="text-align: center">
            <h1>데이터 저장에 실패했습니다.</h1>
            <a href="index.jsp">홈 으로 가기</a>
        </div>
        <% } %>
    </div>
</body>
</html>
