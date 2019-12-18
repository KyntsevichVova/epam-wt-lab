package com.kyntsevichvova.wtlab.servlet;

import com.kyntsevichvova.wtlab.bean.Bet;
import com.kyntsevichvova.wtlab.model.Model;
import com.kyntsevichvova.wtlab.parser.ModelParser;
import com.kyntsevichvova.wtlab.parser.impl.DomParser;
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
public class BetsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Bet> bets = new ArrayList<>();

        Model model = Model.getInstance();

        ModelParser parser = new DomParser();

        try {

            model.init(getServletContext());
            parser.parse(model);
            bets = parser.getBets();

        } catch (Exception e) {
            log.debug("Error while parsing: " + e.getMessage());
        }

        req.setAttribute("bets", bets);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/bets.jsp");
        requestDispatcher.forward(req, resp);
    }
}
