<%--
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
        <h1>즐겨찾기 그룹 추가</h1>
        <div>
            <a href="index.jsp">홈</a> &nbsp;|&nbsp;
            <a href="location-history.jsp">위치 히스토리 목록</a> &nbsp;|&nbsp;
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> &nbsp;|&nbsp;
            <a href="hello-servlet">즐겨찾기 보기</a> &nbsp;|&nbsp;
            <a href="bookmarkGroupList.jsp">즐겨찾기 그룹 관리</a>
        </div>
        <br>
        <div>
            <table>
                <tr>
                    <th>즐겨찾기 이름</th>
                    <td><input style="margin-left: 20px" type="text" id="bookmark-name"></td>
                </tr>
                <tr>
                    <th>순서</th>
                    <td><input style="margin-left: 20px" type="text" id="ordering-number"></td>
                </tr>
                <tr>
                    <td style="text-align: center" colspan="2">
                        <input type="button" value="추가" onclick="addBookMarkGroup()">
                    </td>
                </tr>
            </table>
        </div>
        <script>
            function addBookMarkGroup() {
                let inputBookMarkName = document.getElementById("bookmark-name");
                let inputOrderingNumber = document.getElementById("ordering-number");

                fetch('addBookMarkGroup', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        'bookmark-name': inputBookMarkName.value,
                        'ordering-number': inputOrderingNumber.value
                    })
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    alert("즐겨찾기 그룹이 추가되었습니다.")
                    window.location.href = "bookmarkGroupList.jsp";
                    return response.text();
                });
            }
        </script>
    </body>
</html>
