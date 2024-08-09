<%@ page import="static com.wifi.publicwifiproject.dao.WifiDAO.nearWifiList" %>
<%@ page import="com.wifi.publicwifiproject.dto.WifiDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <h1>와이파이 정보 구하기</h1>
        <div>
            <a href="index.jsp">홈</a> &nbsp;|&nbsp;
            <a href="location-history.jsp">위치 히스토리 목록</a> &nbsp;|&nbsp;
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> &nbsp;|&nbsp;
            <a href="hello-servlet">즐겨찾기 보기</a> &nbsp;|&nbsp;
            <a href="bookmarkGroupList.jsp">즐겨찾기 그룹 관리</a>
        </div>
        <br>
        <%
            String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
            String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
        %>
        <div>
            <label>LAT : </label>
            <input type="text" id="LAT" value=<%=lat%>>
            ,&nbsp;
            <label>LNT : </label>
            <input type="text" id="LNT" value=<%=lnt%>>
            <input type="button" id="btnMyLocation" value="내 위치 가져오기">
            <input type="button" id="btnNearWifiInfo" value="주변 Wifi 정보 가져오기">
        </div>
        <br>
        <div>
            <table>
                <thead>
                    <th>거리(Km)</th>
                    <th>관리번호</th>
                    <th>자치구</th>
                    <th>와이파이명</th>
                    <th>도로명주소</th>
                    <th>상세주소</th>
                    <th>설치위치(층)</th>
                    <th>설치유형</th>
                    <th>설치기관</th>
                    <th>서비스구분</th>
                    <th>망종류</th>
                    <th>설치년도</th>
                    <th>실내외구분</th>
                    <th>Wifi접속환경</th>
                    <th>X 좌표</th>
                    <th>Y 좌표</th>
                    <th>작업일자</th>
                </thead>
                <tbody>
                <%
                    if (!lat.equals("0.0") && !lnt.equals("0.0")) {
                       List<WifiDTO> list = nearWifiList(lat, lnt);

                       if (list.size() > 0) {
                           for (WifiDTO wifiDTO : list) {
                %>
                            <tr>
                                <td><%=wifiDTO.getDistance()%></td>
                                <td><%=wifiDTO.getXSwifiMgrNo()%></td>
                                <td><%=wifiDTO.getXSwifiWrdofc()%></td>
                                <td><a href="detailWifi.jsp?id=<%=wifiDTO.getId()%>"><%=wifiDTO.getXSwifiMainNm()%></a></td>
                                <td><%=wifiDTO.getXSwifiAdres1()%></td>
                                <td><%=wifiDTO.getXSwifiAdres2()%></td>
                                <td><%=wifiDTO.getXSwifiInstlFloor()%></td>
                                <td><%=wifiDTO.getXSwifiInstlTy()%></td>
                                <td><%=wifiDTO.getXSwifiInstlMby()%></td>
                                <td><%=wifiDTO.getXSwifiSvcSe()%></td>
                                <td><%=wifiDTO.getXSwifiCmcwr()%></td>
                                <td><%=wifiDTO.getXSwifiCnstcYear()%></td>
                                <td><%=wifiDTO.getXSwifiInoutDoor()%></td>
                                <td><%=wifiDTO.getXSwifiRemars3()%></td>
                                <td><%=wifiDTO.getLat()%></td>
                                <td><%=wifiDTO.getLnt()%></td>
                                <td><%=wifiDTO.getWorkDttm()%></td>
                            </tr>
                <%
                           }
                       }
                    } else {
                %>
                            <tr>
                                <td colspan="17">
                                    위치 정보를 입력한 후에 조회해 주세요.
                                </td>
                            </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <script>
            let lat = document.getElementById("LAT");
            let lnt = document.getElementById("LNT");

            let btnMyLocation = document.getElementById("btnMyLocation");
            let btnNearWifiInfo = document.getElementById("btnNearWifiInfo");

            btnMyLocation.addEventListener("click", function () {
                if ('geolocation' in navigator) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        lat.value = position.coords.latitude;
                        lnt.value = position.coords.longitude;
                    }, function(error) {
                        console.error('Error occurred. Error code: ' + error.code);
                    }, {
                        enableHighAccuracy: true
                    });
                } else {
                    alert("위치 정보를 확인할 수 없습니다. 위치 정보를 입력해주세요.")
                }
            });

            btnNearWifiInfo.addEventListener("click", function () {
                let url = new URL(window.location.href);

                if (lat.value !== "0.0" || lnt.value !== "0.0") {
                    url.searchParams.set("lat", lat.value);
                    url.searchParams.set("lnt", lnt.value);

                    window.location.href = url.href;
                } else {
                    alert("나의 위치정보 가져오기를 클릭하세요!")
                }
            });
        </script>
    </body>
</html>