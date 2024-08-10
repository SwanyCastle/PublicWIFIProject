<%@ page import="com.wifi.publicwifiproject.dto.WifiDTO" %>
<%@ page import="static com.wifi.publicwifiproject.dao.WifiDAO.detailWifiInfo" %>
<%@ page import="com.wifi.publicwifiproject.dto.BookMarkGroupDTO" %>
<%@ page import="static com.wifi.publicwifiproject.dao.BookMarkGroupDAO.bookMarkGroupList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ksh
  Date: 2024. 8. 9.
  Time: PM 2:52
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
            int id = Integer.parseInt(request.getParameter("id"));
            WifiDTO wifiDTO = detailWifiInfo(id);
            List<BookMarkGroupDTO> list = bookMarkGroupList();
        %>
        <h1>와이파이 상세 정보 - (<%=wifiDTO.getXSwifiMainNm()%>)</h1>
        <div>
            <a href="index.jsp">홈</a> &nbsp;|&nbsp;
            <a href="location-history.jsp">위치 히스토리 목록</a> &nbsp;|&nbsp;
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> &nbsp;|&nbsp;
            <a href="bookmarkList.jsp">즐겨찾기 보기</a> &nbsp;|&nbsp;
            <a href="bookmarkGroupList.jsp">즐겨찾기 그룹 가져오기</a>
        </div>
        <br>
        <div>
            <select name="bookmarkGroup" id="bookmarkGroup">
                <option value="" selected>즐겨찾기 그룹 이름 선택</option>
                <%
                    for (BookMarkGroupDTO bmgDTO : list) {
                %>
                <option name="bookmarkGroup" value="<%=bmgDTO.getId()%>"><%=bmgDTO.getGroupName()%></option>
                <% } %>
            </select>
            <input type="button" value="즐겨찾기 추가하기" onclick="addBookMark(<%=wifiDTO.getId()%>)">
        </div>
        <br>
        <div>
            <table>
                <tr>
                    <th>거리(Km)</th>
                    <td><%=wifiDTO.getDistance()%></td>
                </tr>
                <tr>
                    <th>관리번호</th>
                    <td><%=wifiDTO.getXSwifiMgrNo()%></td>
                </tr>
                <tr>
                    <th>자치구</th>
                    <td><%=wifiDTO.getXSwifiWrdofc()%></td>
                </tr>
                <tr>
                    <th>와이파이명</th>
                    <td><%=wifiDTO.getXSwifiMainNm()%></td>
                </tr>
                <tr>
                    <th>도로명주소</th>
                    <td><%=wifiDTO.getXSwifiAdres1()%></td>
                </tr>
                <tr>
                    <th>상세주소</th>
                    <td><%=wifiDTO.getXSwifiAdres2()%></td>
                </tr>
                <tr>
                    <th>설치위치(층)</th>
                    <td><%=wifiDTO.getXSwifiInstlFloor()%></td>
                </tr>
                <tr>
                    <th>설치유형</th>
                    <td><%=wifiDTO.getXSwifiInstlTy()%></td>
                </tr>
                <tr>
                    <th>설치기관</th>
                    <td><%=wifiDTO.getXSwifiInstlMby()%></td>
                </tr>
                <tr>
                    <th>서비스구분</th>
                    <td><%=wifiDTO.getXSwifiSvcSe()%></td>
                </tr>
                <tr>
                    <th>망종류</th>
                    <td><%=wifiDTO.getXSwifiCmcwr()%></td>
                </tr>
                <tr>
                    <th>설치년도</th>
                    <td><%=wifiDTO.getXSwifiCnstcYear()%></td>
                </tr>
                <tr>
                    <th>실내외구분</th>
                    <td><%=wifiDTO.getXSwifiInoutDoor()%></td>
                </tr>
                <tr>
                    <th>WIFI접속환경</th>
                    <td><%=wifiDTO.getXSwifiRemars3()%></td>
                </tr>
                <tr>
                    <th>X좌표</th>
                    <td><%=wifiDTO.getLat()%></td>
                </tr>
                <tr>
                    <th>Y좌표</th>
                    <td><%=wifiDTO.getLnt()%></td>
                </tr>
                <tr>
                    <th>작업일자</th>
                    <td><%=wifiDTO.getWorkDttm()%></td>
                </tr>
            </table>
        </div>
        <script>
            function addBookMark(wifiId) {
                let bookmarks = document.getElementById("bookmarkGroup");
                let bookmarkId = bookmarks.options[bookmarks.selectedIndex].value;

                fetch('addBookMark', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        'wifi-id': wifiId,
                        'bookmark-id': bookmarkId
                    })
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    alert("즐겨찾기가 추가되었습니다.")
                    window.location.href = "bookmarkList.jsp";
                    return response.text();
                });
            }
        </script>
    </body>
</html>
