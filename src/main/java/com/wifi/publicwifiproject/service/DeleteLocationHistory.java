package com.wifi.publicwifiproject.service;

import com.wifi.publicwifiproject.dao.LocationHistoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteLocation")
public class DeleteLocationHistory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                LocationHistoryDAO dao = new LocationHistoryDAO();
                boolean success = dao.deleteLocationHistory(id);

                response.setContentType("text/plain");
                response.getWriter().write(success ? "success" : "failure");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.getWriter().write("error");
            }
        } else {
            response.getWriter().write("error");
        }
    }
}
