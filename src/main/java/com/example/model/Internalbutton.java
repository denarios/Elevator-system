package com.example.model;

import com.example.controller.InternalButtonController;

public class Internalbutton {
    private final int elevatorId;
    private final InternalButtonController controller;

    public Internalbutton(int elevatorId, InternalButtonController controller) {
        this.elevatorId = elevatorId;
        this.controller = controller;
    }

    public void press(int floor) {
        controller.submitRequest(elevatorId, floor);
    }
}
