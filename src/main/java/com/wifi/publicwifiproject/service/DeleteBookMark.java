package com.wifi.publicwifiproject.service;

import com.wifi.publicwifiproject.dao.BookMarkDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteBookMark")
public class DeleteBookMark extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                BookMarkDAO bmDAO = new BookMarkDAO();
                bmDAO.deleteBookMark(id);
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid ID format");
            }
        } else {
            response.getWriter().write("error");
        }
    }
}
