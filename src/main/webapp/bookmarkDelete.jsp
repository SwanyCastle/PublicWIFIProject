<%@ page import="com.wifi.publicwifiproject.dto.BookMarkDTO" %>
<%@ page import="static com.wifi.publicwifiproject.dao.BookMarkDAO.detailBookMark" %>
<%@ page import="com.wifi.publicwifiproject.dao.BookMarkDAO" %><%--
  Created by IntelliJ IDEA.
  User: ksh
  Date: 2024. 8. 9.
  Time: PM 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <style>
            .input {
                margin-bottom: 10px;
            }

            table, td, th {
                border: 1px solid #dadad8;
                padding: 15px 1px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                border-spacing: 0 1rem;
            }

            th {
                font-size: 12px;
                color: white;
                background-color: #029d5f;
            }

            td {
                height: 100%;
                color: black;
                font-size: 12px;
            }

            tr:hover {
                background-color: lightgray;
            }
        </style>
    </head>
    <body>
        <%
            int id = Integer.parseInt(request.getParameter("id"));
            BookMarkDTO bmDTO = detailBookMark(id);
        %>
        <h1>즐겨찾기 삭제</h1>
        <div>
            <a href="index.jsp">홈</a> &nbsp;|&nbsp;
            <a href="location-history.jsp">위치 히스토리 목록</a> &nbsp;|&nbsp;
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> &nbsp;|&nbsp;
            <a href="bookmarkList.jsp">즐겨찾기 보기</a> &nbsp;|&nbsp;
            <a href="bookmarkGroupList.jsp">즐겨찾기 그룹 가져오기</a>
        </div>
        <br>
        <p>즐겨찾기를 삭제하시겠습니까?</p>
        <br>
        <div>
            <table>
                <tr>
                    <th>즐겨찾기 이름</th>
                    <td><%=bmDTO.getBookmarkName()%></td>
                </tr>
                <tr>
                    <th>와이파이명</th>
                    <td><%=bmDTO.getWifiName()%></td>
                </tr>
                <tr>
                    <th>등록일자</th>
                    <td><%=bmDTO.getCreated_at()%></td>
                </tr>
                <tr>
                    <td style="text-align: center" colspan="2">
                        <input type="button" value="삭제" onclick="deleteBookMark(<%=bmDTO.getId()%>)">
                    </td>
                </tr>
            </table>
        </div>
        <script>
            function deleteBookMark(id) {
                if (confirm("정말로 삭제하시겠습니까?")) {
                    <%
                        BookMarkDAO bmDAO = new BookMarkDAO();
                        boolean result = bmDAO.deleteBookMark(id);

                        if (result) {
                    %>
                    alert("정상적으로 삭제되었습니다.")
                    window.location.href = "bookmarkList.jsp";
                    <% } else { %>
                    alert("삭제되지 않았습니다.")
                    <% } %>

//  이거만 왜 안되는지 모르겠음 다른건 다되는데 ㅡㅡ 위에 임시방편 ㅡㅡ
//                     fetch('deleteBookMark', {
//                         method: 'POST',
//                         headers: {
//                             'Content-Type': 'application/x-www-form-urlencoded'
//                         },
//                         body: new URLSearchParams({
//                             'id': id
//                         })
//                     })
//                     .then(response => {
//                         if (!response.ok) {
//                             throw new Error('Network response was not ok');
//                         }
//                         alert("정상적으로 삭제되었습니다.")
//                         window.location.href = "bookmarkList.jsp";
//                         return response.text();
//                     })
//                     .then(data => {
//                         console.log('Success:', data);
//                         document.getElementById("row" + id).remove();
//                     })
//                     .catch(error => {
//                         console.error('Error:', error);
//                     });
                }
            }
        </script>
    </body>
</html>
