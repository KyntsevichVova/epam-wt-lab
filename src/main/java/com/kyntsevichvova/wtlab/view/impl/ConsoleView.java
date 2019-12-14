package com.kyntsevichvova.wtlab.view.impl;

import com.kyntsevichvova.wtlab.controller.Controller;
import com.kyntsevichvova.wtlab.view.View;

import java.util.Scanner;

public class ConsoleView implements View {
    private boolean isRunning = true;

    @Override
    public void start() {
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String input = scanner.nextLine().trim();

            String response;
            response = controller.executeTask(input);

            System.out.println(response);
        }
    }
}
