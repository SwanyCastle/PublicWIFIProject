<%@ page import="com.wifi.publicwifiproject.dto.BookMarkGroupDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="static com.wifi.publicwifiproject.dao.BookMarkGroupDAO.bookMarkGroupList" %><%--
  Created by IntelliJ IDEA.
  User: ksh
  Date: 2024. 8. 9.
  Time: PM 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <h1>즐겨찾기 그룹 관리</h1>
        <div>
            <a href="index.jsp">홈</a> &nbsp;|&nbsp;
            <a href="location-history.jsp">위치 히스토리 목록</a> &nbsp;|&nbsp;
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> &nbsp;|&nbsp;
            <a href="bookmarkList.jsp">즐겨찾기 보기</a> &nbsp;|&nbsp;
            <a href="bookmarkGroupList.jsp">즐겨찾기 그룹 관리</a>
        </div>
        <br>
        <div>
            <input type="button" value="즐겨찾기 그룹 추가" onclick="location.href='bookmarkGroupAdd.jsp'">
        </div>
        <br>
        <table>
            <thead>
                <th>ID</th>
                <th>즐겨찾기 이름</th>
                <th>순서</th>
                <th>등록일자</th>
                <th>수정일자</th>
                <th>비고</th>
            </thead>
            <tbody>
                <%
                    List<BookMarkGroupDTO> list = bookMarkGroupList();

                    if (list.size() > 0) {
                        for (BookMarkGroupDTO bookMarkGroupDTO : list) {
                %>
                    <tr>
                        <td><%=bookMarkGroupDTO.getId()%></td>
                        <td><%=bookMarkGroupDTO.getGroupName()%></td>
                        <td><%=bookMarkGroupDTO.getOrderingNumber()%></td>
                        <td><%=bookMarkGroupDTO.getCreatedAt()%></td>
                        <td>
                            <%
                                if (bookMarkGroupDTO.getUpdatedAt() != null) {
                            %>
                                    <%=bookMarkGroupDTO.getUpdatedAt()%>
                            <% } %>
                        </td>
                        <td>
                            <a href="bookmarkGroupEdit.jsp?id=<%=bookMarkGroupDTO.getId()%>">수정</a>
                            &nbsp;
                            <a href="" onclick="deleteBookMarkGroup(<%=bookMarkGroupDTO.getId()%>)">삭제</a>
                        </td>
                    </tr>
                <%      }
                    } else {
                %>
                    <td colspan="6">
                        등록된 북마크 그룹이 없습니다.
                    </td>
                <% } %>
            </tbody>
        </table>
        <script>
            function deleteBookMarkGroup(id) {
                if (confirm("정말로 삭제하시겠습니까?")) {
                    fetch('deleteBookMarkGroup', {
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
                    });
                }
            }
        </script>
    </body>
</html>
