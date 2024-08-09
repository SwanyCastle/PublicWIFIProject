<%@ page import="com.wifi.publicwifiproject.dao.LocationHistoryDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wifi.publicwifiproject.dto.LocationHistoryDTO" %><%--
  Created by IntelliJ IDEA.
  User: ksh
  Date: 2024. 8. 7.
  Time: PM 7:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <h1>위치 히스토리 목록</h1>
        <div>
            <a href="index.jsp">홈</a> &nbsp;|&nbsp;
            <a href="location-history.jsp">위치 히스토리 목록</a> &nbsp;|&nbsp;
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> &nbsp;|&nbsp;
            <a href="hello-servlet">즐겨찾기 보기</a> &nbsp;|&nbsp;
            <a href="bookmarkGroupList.jsp">즐겨찾기 그룹 관리</a>
        </div>
        <br>
        <div>
            <%
                LocationHistoryDAO locationHistoryDAO = new LocationHistoryDAO();
                List<LocationHistoryDTO> list = locationHistoryDAO.locHistoryList();
            %>
            <table>
                <thead>
                    <th>ID</th>
                    <th>X좌표</th>
                    <th>Y좌표</th>
                    <th>조회일자</th>
                    <th>비고</th>
                </thead>
                <tbody>
                    <% if (list.size() > 0) { %>
                        <% for (LocationHistoryDTO locationHistoryDTO : list) { %>
                        <tr>
                            <td><%=locationHistoryDTO.getId()%></td>
                            <td><%=locationHistoryDTO.getLat()%></td>
                            <td><%=locationHistoryDTO.getLnt()%></td>
                            <td><%=locationHistoryDTO.getSearchDate()%></td>
                            <td><input type="button" value="삭제" id="<%=locationHistoryDTO.getId()%>"
                                       onclick="deleteLocationHistory(id)"></td>
                        </tr>
                        <% } %>
                    <% } %>
                </tbody>
            </table>
        </div>
        <script>
            function deleteLocationHistory(id) {
                if (confirm("정말로 삭제하시겠습니까?")) {
                    fetch('deleteLocation', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: new URLSearchParams({
                            'id': id
                        })
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        alert("정상적으로 삭제되었습니다.")
                        window.location.reload(true);
                        return response.text();
                    })
                    .then(data => {
                        console.log('Success:', data);
                        document.getElementById("row" + id).remove();
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
                }
            }
        </script>
    </body>
</html>
