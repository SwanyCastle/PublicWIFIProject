package com.wifi.publicwifiproject.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wifi.publicwifiproject.DBConnection.DBConnection;
import com.wifi.publicwifiproject.dto.WifiDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDAO {
    private static Connection connection = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;

    public static int insertWifiInfo(JsonArray jsonArray) {
        connection = DBConnection.connectDB();

        int count = 0;
        try {
            String inserQuery = " insert ignore into wifi_info ( " +
                    "x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, " +
                    "x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, " +
                    "x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm ) " +
                    " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            preparedStatement = connection.prepareStatement(inserQuery);

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonData = jsonArray.get(i).getAsJsonObject();

                preparedStatement.setString(1, jsonData.get("X_SWIFI_MGR_NO").getAsString());
                preparedStatement.setString(2, jsonData.get("X_SWIFI_WRDOFC").getAsString());
                preparedStatement.setString(3, jsonData.get("X_SWIFI_MAIN_NM").getAsString());
                preparedStatement.setString(4, jsonData.get("X_SWIFI_ADRES1").getAsString());
                preparedStatement.setString(5, jsonData.get("X_SWIFI_ADRES2").getAsString());
                preparedStatement.setString(6, jsonData.get("X_SWIFI_INSTL_FLOOR").getAsString());
                preparedStatement.setString(7, jsonData.get("X_SWIFI_INSTL_TY").getAsString());
                preparedStatement.setString(8, jsonData.get("X_SWIFI_INSTL_MBY").getAsString());
                preparedStatement.setString(9, jsonData.get("X_SWIFI_SVC_SE").getAsString());
                preparedStatement.setString(10, jsonData.get("X_SWIFI_CMCWR").getAsString());
                preparedStatement.setString(11, jsonData.get("X_SWIFI_CNSTC_YEAR").getAsString());
                preparedStatement.setString(12, jsonData.get("X_SWIFI_INOUT_DOOR").getAsString());
                preparedStatement.setString(13, jsonData.get("X_SWIFI_REMARS3").getAsString());
                preparedStatement.setString(14, jsonData.get("LAT").getAsString());
                preparedStatement.setString(15, jsonData.get("LNT").getAsString());
                preparedStatement.setString(16, jsonData.get("WORK_DTTM").getAsString());

                preparedStatement.addBatch();
                preparedStatement.clearParameters();

                if ((i + 1) % 1000 == 0) {
                    int[] result = preparedStatement.executeBatch();
                    count += result.length;
                    connection.commit();
                }
            }
            int[] result = preparedStatement.executeBatch();
            count += result.length;
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static List<WifiDTO> nearWifiList(String lat, String lnt) {
        connection = DBConnection.connectDB();

        List<WifiDTO> wifiDTOList = new ArrayList<>();

        String selectQuery = " select *, " +
                "round((6371 * acos(cos(radians(?)) * cos(radians(lat)) * cos(radians(lnt) - radians(?)) + sin(radians(?)) * sin(radians(lat)))), 4) AS distance " +
                "from wifi_info " +
                "order by distance " +
                "limit 20; ";

        try {
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setDouble(1, Double.parseDouble(lat));
            preparedStatement.setDouble(2, Double.parseDouble(lnt));
            preparedStatement.setDouble(3, Double.parseDouble(lat));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                WifiDTO wifiDTO = new WifiDTO();
                wifiDTO.setDistance(resultSet.getDouble("distance"));
                wifiDTO.setXSwifiMgrNo(resultSet.getString("x_swifi_mgr_no"));
                wifiDTO.setXSwifiWrdofc(resultSet.getString("x_swifi_wrdofc"));
                wifiDTO.setXSwifiMainNm(resultSet.getString("x_swifi_main_nm"));
                wifiDTO.setXSwifiAdres1(resultSet.getString("x_swifi_adres1"));
                wifiDTO.setXSwifiAdres2(resultSet.getString("x_swifi_adres2"));
                wifiDTO.setXSwifiInstlFloor(resultSet.getString("x_swifi_instl_floor"));
                wifiDTO.setXSwifiInstlTy(resultSet.getString("x_swifi_instl_ty"));
                wifiDTO.setXSwifiInstlMby(resultSet.getString("x_swifi_instl_mby"));
                wifiDTO.setXSwifiSvcSe(resultSet.getString("x_swifi_svc_se"));
                wifiDTO.setXSwifiCmcwr(resultSet.getString("x_swifi_cmcwr"));
                wifiDTO.setXSwifiCnstcYear(resultSet.getString("x_swifi_cnstc_year"));
                wifiDTO.setXSwifiInoutDoor(resultSet.getString("x_swifi_inout_door"));
                wifiDTO.setXSwifiRemars3(resultSet.getString("x_swifi_remars3"));
                wifiDTO.setLat(resultSet.getString("lat"));
                wifiDTO.setLnt(resultSet.getString("lnt"));
                wifiDTO.setWorkDttm(resultSet.getString("work_dttm"));

                wifiDTOList.add(wifiDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 여기서 위치정보 히스토리테이블에 저장
        LocationHistoryDAO.insertLocHistory(lat, lnt);
        return wifiDTOList;
    }
}
