package com.wifi.publicwifiproject.service;

import com.wifi.publicwifiproject.dao.BookMarkGroupDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteBookMarkGroup")
public class DeleteBookMarkGroup extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                BookMarkGroupDAO bmgDAO = new BookMarkGroupDAO();
                bmgDAO.deleteBookMark(id);
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
