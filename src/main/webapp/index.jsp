<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <style>
            table {
                width: 100%;
            }
            th, td {
                border: 1px solid black;
                border-collapse: collapse;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1><%= "와이파이 정보 구하기" %></h1>
        <a href="">홈</a> &nbsp;|&nbsp;
        <a href="hello-servlet">위치 히스토리 목록</a> &nbsp;|&nbsp;
        <a href="hello-servlet">Open API 와이파이 정보 가져오기</a>
        <br>
        <br>
        <label>LAT : </label>
        <input type="text" id="LAT">
        ,&nbsp;
        <label>LNT : </label>
        <input type="text" id="LNT">
        <input type="button" id="myLocation" value="내 위치 가져오기">
        <input type="button" id="aroundWifiInfo" value="주변 Wifi 정보 가져오기">
        <br>
        <br>
        <table>
            <thead>
            <th>
                거리(Km)
            </th>
            <th>
                관리번호
            </th>
            <th>
                자치구
            </th>
            <th>
                와이파이명
            </th>
            <th>
                도로명주소
            </th>
            <th>
                상세주소
            </th>
            <th>
                설치위치(층)
            </th>
            <th>
                설치유형
            </th>
            <th>
                설치기관
            </th>
            <th>
                서비스구분
            </th>
            <th>
                망종류
            </th>
            <th>
                설치년도
            </th>
            <th>
                실내외구분
            </th>
            <th>
                Wifi접속환경
            </th>
            <th>
                X 좌표
            </th>
            <th>
                Y 좌표
            </th>
            <th>
                작업일자
            </th>
            </thead>
            <tbody>
            <td colspan="17">
                위치 정보를 입력한 후에 조회해 주세요.
            </td>
            </tbody>
        </table>
    </body>
</html>