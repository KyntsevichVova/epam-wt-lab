package com.kyntsevichvova.wtlab.servlet;

import com.kyntsevichvova.wtlab.bean.Race;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RacesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Race> races = new ArrayList<>();

        try {
            //StaXParser parser = new StaXParser();

        } catch (Exception e) {

        }

        req.setAttribute("races", races);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/races.jsp");
        requestDispatcher.forward(req, resp);
    }
}
