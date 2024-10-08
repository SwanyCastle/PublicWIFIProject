package com.wifi.publicwifiproject.dao;

import com.wifi.publicwifiproject.DBConnection.DBConnection;
import com.wifi.publicwifiproject.dto.BookMarkGroupDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMarkGroupDAO {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public void insertBookMark(String bookmarkName, int orderingNumber) {
        try {
            connection = DBConnection.connectDB();
            String insertQuery = " insert ignore into bookmark_group (group_name, ordering_number) values (?, ?); ";            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, bookmarkName);
            preparedStatement.setInt(2, orderingNumber);

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

    public static List<BookMarkGroupDTO> bookMarkGroupList() {
        List<BookMarkGroupDTO> list = new ArrayList<>();
        try {
            connection = DBConnection.connectDB();
            String selectQuery = " select * from bookmark_group order by ordering_number; ";
            preparedStatement = connection.prepareStatement(selectQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookMarkGroupDTO bmgDTO = new BookMarkGroupDTO();
                bmgDTO.setId(resultSet.getInt("id"));
                bmgDTO.setGroupName(resultSet.getString("group_name"));
                bmgDTO.setOrderingNumber(resultSet.getInt("ordering_number"));
                bmgDTO.setCreatedAt(resultSet.getString("created_at"));
                bmgDTO.setUpdatedAt(resultSet.getString("updated_at"));
                list.add(bmgDTO);
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

    public static BookMarkGroupDTO detailBookMarkGroup(int id) {
        BookMarkGroupDTO bmgDTO = new BookMarkGroupDTO();
        try {
            connection = DBConnection.connectDB();
            String selectQuery = " select * from bookmark_group where id = ?; ";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bmgDTO.setId(resultSet.getInt("id"));
                bmgDTO.setGroupName(resultSet.getString("group_name"));
                bmgDTO.setOrderingNumber(resultSet.getInt("ordering_number"));
                bmgDTO.setCreatedAt(resultSet.getString("created_at"));
                bmgDTO.setUpdatedAt(resultSet.getString("updated_at"));
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
        return bmgDTO;
    }

    public void updateBookMark(int id, String bookmarkName, int orderingNumber) {
        try {
            connection = DBConnection.connectDB();
            String updateQuery = " update bookmark_group set group_name = ?, ordering_number = ? where id = ?";            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, bookmarkName);
            preparedStatement.setInt(2, orderingNumber);
            preparedStatement.setInt(3, id);

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

    public void deleteBookMark(int id) {
        try {
            connection = DBConnection.connectDB();
            String sql = "delete from bookmark_group where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
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
    }
}
