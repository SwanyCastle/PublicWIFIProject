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

    public void insertLocHistory(String lat, String lnt) {
        try {
            connection = DBConnection.connectDB();
            String insertQuery = " insert into location_history (lat, lnt, search_date) values (?, ?, ?)";
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());            preparedStatement = connection.prepareStatement(insertQuery);
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
        List<LocationHistoryDTO> list = new ArrayList<>();
        try {
            connection = DBConnection.connectDB();
            String selectQuery = " select * from location_history order by search_date desc; ";            preparedStatement = connection.prepareStatement(selectQuery);
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

    public boolean deleteLocationHistory(int id) {
        boolean result = false;
        try {
            connection = DBConnection.connectDB();
            String sql = "delete from location_history where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            result = (rowsAffected > 0);

        } catch (SQLException e) {
            e.printStackTrace();
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
        return result;
    }
}
