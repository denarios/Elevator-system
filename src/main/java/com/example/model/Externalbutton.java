package com.example.model;

import com.example.controller.ExternalController;
import com.example.enums.Directions;


// Wrong this need to call ExternalButton Controller

public class Externalbutton {

    private final ExternalController externalController;

    public Externalbutton(ExternalController externalController) {
        this.externalController = externalController;
    }

    public void press(int floor, Directions direction) {
        externalController.submitRequest(floor, direction);
    }
}
