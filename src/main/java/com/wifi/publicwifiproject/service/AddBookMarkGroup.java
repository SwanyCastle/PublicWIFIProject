package com.wifi.publicwifiproject.service;

import com.wifi.publicwifiproject.dao.BookMarkGroupDAO;
import com.wifi.publicwifiproject.dao.LocationHistoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addBookMarkGroup")
public class AddBookMarkGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookmarkNameParam = request.getParameter("bookmark-name");
        String orderingNumberParam = request.getParameter("ordering-number");
        if (bookmarkNameParam != null && !bookmarkNameParam.isEmpty()
            || orderingNumberParam != null && !orderingNumberParam.isEmpty()) {
            try {
                int orderingNumber = Integer.parseInt(orderingNumberParam);
                BookMarkGroupDAO bmgDAO = new BookMarkGroupDAO();
                bmgDAO.insertBookMark(bookmarkNameParam, orderingNumber);

                response.setContentType("text/plain");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.getWriter().write("error");
            }
        } else {
            response.getWriter().write("error");
        }
    }
}
