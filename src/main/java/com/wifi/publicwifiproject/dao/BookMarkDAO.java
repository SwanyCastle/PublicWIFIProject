package com.wifi.publicwifiproject.dao;

import com.wifi.publicwifiproject.DBConnection.DBConnection;
import com.wifi.publicwifiproject.dto.BookMarkDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMarkDAO {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public void insertBookMark(int wifiId, int bookmarkId) {
        try {
            connection = DBConnection.connectDB();
            String insertQuery = " insert into bookmark (wifi_id, bookmark_group_id) values (?, ?); ";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, wifiId);
            preparedStatement.setInt(2, bookmarkId);
            int checkNum = preparedStatement.executeUpdate();

            if (checkNum > 0) {
                connection.commit();
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
    }

    public static List<BookMarkDTO> bookmarkList() {
        List<BookMarkDTO> list = new ArrayList<>();
        try {
            connection = DBConnection.connectDB();
            String selectQuery = " select bm.id, bm.wifi_id, wi.x_swifi_main_nm, bm.bookmark_group_id, bmg.group_name, bm.created_at " +
                                    "from bookmark as bm " +
                                    "join bookmark_group as bmg on bm.bookmark_group_id = bmg.id " +
                                    "join wifi_info as wi on bm.wifi_id = wi.id; ";
            preparedStatement = connection.prepareStatement(selectQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookMarkDTO bmDTO = new BookMarkDTO();
                bmDTO.setId(resultSet.getInt("bm.id"));
                bmDTO.setWifiId(resultSet.getInt("bm.wifi_id"));
                bmDTO.setWifiName(resultSet.getString("wi.x_swifi_main_nm"));
                bmDTO.setBookmarkId(resultSet.getInt("bm.bookmark_group_id"));
                bmDTO.setBookmarkName(resultSet.getString("bmg.group_name"));
                bmDTO.setCreated_at(resultSet.getString("bm.created_at"));
                list.add(bmDTO);
            }
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
        return list;
    }

    public static BookMarkDTO detailBookMark(int id) {
        BookMarkDTO bmDTO = new BookMarkDTO();
        try {
            connection = DBConnection.connectDB();
            String selectQuery = " select bm.id, bm.wifi_id, wi.x_swifi_main_nm, bm.bookmark_group_id, bmg.group_name, bm.created_at " +
                                    "from bookmark as bm " +
                                    "join bookmark_group as bmg on bm.bookmark_group_id = bmg.id " +
                                    "join wifi_info as wi on bm.wifi_id = wi.id where bm.id = ?; ";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bmDTO.setId(resultSet.getInt("bm.id"));
                bmDTO.setWifiId(resultSet.getInt("bm.wifi_id"));
                bmDTO.setWifiName(resultSet.getString("wi.x_swifi_main_nm"));
                bmDTO.setBookmarkId(resultSet.getInt("bm.bookmark_group_id"));
                bmDTO.setBookmarkName(resultSet.getString("bmg.group_name"));
                bmDTO.setCreated_at(resultSet.getString("bm.created_at"));
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
        return bmDTO;
    }

    public boolean deleteBookMark(int id) {
        boolean result = false;
        try {
            connection = DBConnection.connectDB();
            String sql = "delete from bookmark where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int checkNum = preparedStatement.executeUpdate();
            result = checkNum > 0;
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
