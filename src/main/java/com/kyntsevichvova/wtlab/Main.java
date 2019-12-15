package com.kyntsevichvova.wtlab;

import com.kyntsevichvova.wtlab.view.View;
import com.kyntsevichvova.wtlab.view.factory.ViewFactory;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {
    public static void main(String[] args) {
        log.debug("Entered main");
        View view = ViewFactory.getInstance().getView();
        view.start();
    }
}
