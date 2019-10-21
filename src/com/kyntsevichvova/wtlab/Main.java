package com.kyntsevichvova.wtlab;

import com.kyntsevichvova.wtlab.view.View;
import com.kyntsevichvova.wtlab.view.factory.ViewFactory;

public class Main {
    public static void main(String[] args) {
        View view = ViewFactory.getInstance().getView();
        view.start();
    }
}
