package com.kyntsevichvova.wtlab.servlet;

import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.model.Model;
import com.kyntsevichvova.wtlab.parser.ModelParser;
import com.kyntsevichvova.wtlab.parser.impl.SaxParser;
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
public class HorsesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Request for HORSES");

        List<Horse> horses = new ArrayList<>();

        Model model = Model.getInstance();
        model.init(getServletContext());

        ModelParser parser = new SaxParser();

        try {

            parser.parse(model);
            horses = parser.getHorses();

        } catch (Exception e) {
            log.debug("Error while parsing: " + e.getMessage());
        }

        req.setAttribute("horses", horses);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/horses.jsp");
        requestDispatcher.forward(req, resp);
    }

}
