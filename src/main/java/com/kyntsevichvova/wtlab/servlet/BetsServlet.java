package com.kyntsevichvova.wtlab.servlet;

import com.kyntsevichvova.wtlab.bean.Bet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BetsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Bet> bets = new ArrayList<>();

        try {
            //DomParser parser = new DomParser();

        } catch (Exception e) {

        }

        req.setAttribute("bets", bets);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/bets.jsp");
        requestDispatcher.forward(req, resp);
    }
}
