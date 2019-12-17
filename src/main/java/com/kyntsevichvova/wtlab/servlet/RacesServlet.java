package com.kyntsevichvova.wtlab.servlet;

import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.model.Model;
import com.kyntsevichvova.wtlab.parser.ModelParser;
import com.kyntsevichvova.wtlab.parser.impl.StaXParser;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class RacesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Race> races = new ArrayList<>();

        Model model = Model.getInstance();
        model.init(getServletContext());

        ModelParser parser = new StaXParser();

        try {

            parser.parse(model);
            races = parser.getRaces();

        } catch (Exception e) {
            log.debug("Error while parsing: " + e.getMessage());
        }


        req.setAttribute("races", races);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/races.jsp");
        requestDispatcher.forward(req, resp);
    }
}
