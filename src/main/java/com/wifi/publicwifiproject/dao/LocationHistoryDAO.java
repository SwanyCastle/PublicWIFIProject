package com.wifi.publicwifiproject.dao;

import com.wifi.publicwifiproject.DBConnection.DBConnection;
import com.wifi.publicwifiproject.dto.LocationHistoryDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocationHistoryDAO {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public static void insertLocHistory(String lat, String lnt) {
        connection = DBConnection.connectDB();

        String insertQuery = " insert into location_history (lat, lnt, search_date) values (?, ?, ?)";

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDouble(1, Double.parseDouble(lat));
            preparedStatement.setDouble(2, Double.parseDouble(lnt));
            preparedStatement.setTimestamp(3, now);
            int checkNum = preparedStatement.executeUpdate();

            if (checkNum > 0) {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                e.printStackTrace();
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
    }

    public List<LocationHistoryDTO> locHistoryList () {
        connection = DBConnection.connectDB();
        List<LocationHistoryDTO> list = new ArrayList<>();
        String selectQuery = " select * from location_history order by search_date desc; ";
        try {
            preparedStatement = connection.prepareStatement(selectQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LocationHistoryDTO locationHistoryDTO = new LocationHistoryDTO();
                locationHistoryDTO.setId(resultSet.getInt("id"));
                locationHistoryDTO.setLat(resultSet.getDouble("lat"));
                locationHistoryDTO.setLnt(resultSet.getDouble("lnt"));
                locationHistoryDTO.setSearchDate(resultSet.getString("search_date"));
                list.add(locationHistoryDTO);
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
        return list;
    }
}
