package com.wifi.publicwifiproject.service;

import com.wifi.publicwifiproject.dao.BookMarkDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addBookMark")
public class AddBookMark extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wifiIdParam = request.getParameter("wifi-id");
        String bookmarkIdParam = request.getParameter("bookmark-id");
        if (wifiIdParam != null && !wifiIdParam.isEmpty()
                || bookmarkIdParam != null && !bookmarkIdParam.isEmpty()) {
            int wifiId = Integer.parseInt(wifiIdParam);
            int bookmarkId = Integer.parseInt(bookmarkIdParam);
            BookMarkDAO bmDAO = new BookMarkDAO();
            bmDAO.insertBookMark(wifiId, bookmarkId);
        } else {
            response.getWriter().write("error");
        }
    }
}
