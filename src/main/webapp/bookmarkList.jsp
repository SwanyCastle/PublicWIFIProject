<%@ page import="com.wifi.publicwifiproject.dto.BookMarkDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="static com.wifi.publicwifiproject.dao.BookMarkDAO.bookmarkList" %><%--
  Created by IntelliJ IDEA.
  User: ksh
  Date: 2024. 8. 10.
  Time: AM 11:40
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
            List<BookMarkDTO> list = bookmarkList();
        %>
        <h1>즐겨찾기 목록</h1>
        <div>
            <a href="index.jsp">홈</a> &nbsp;|&nbsp;
            <a href="location-history.jsp">위치 히스토리 목록</a> &nbsp;|&nbsp;
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> &nbsp;|&nbsp;
            <a href="bookmarkList.jsp">즐겨찾기 보기</a> &nbsp;|&nbsp;
            <a href="bookmarkGroupList.jsp">즐겨찾기 그룹 가져오기</a>
        </div>
        <br>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>북마크 이름</th>
                        <th>와이파이명</th>
                        <th>등록일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (list.size() > 0) {
                            for (BookMarkDTO bookMarkDTO : list) {
                    %>
                            <tr>
                                <td><%=bookMarkDTO.getId()%></td>
                                <td><%=bookMarkDTO.getBookmarkName()%></td>
                                <td><%=bookMarkDTO.getWifiName()%></td>
                                <td><%=bookMarkDTO.getCreated_at()%></td>
                                <td><a href="bookmarkDelete.jsp?id=<%=bookMarkDTO.getId()%>">삭제</a></td>
                            </tr>
                            <% } %>
                    <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
