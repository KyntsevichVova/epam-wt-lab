package com.kyntsevichvova.wtlab.view.factory;

import com.kyntsevichvova.wtlab.view.View;
import com.kyntsevichvova.wtlab.view.impl.ConsoleView;

public class ViewFactory {
    private final static ViewFactory instance = new ViewFactory();

    private final View view = new ConsoleView();

    private ViewFactory() {}

    public static ViewFactory getInstance() {
        return instance;
    }

    public View getView() {
        return view;
    }
}
