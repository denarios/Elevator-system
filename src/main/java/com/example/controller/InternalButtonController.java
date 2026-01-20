package com.example.controller;

public class InternalButtonController {

    private final ElevatorController elevatorController;

    public InternalButtonController(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    public void submitRequest(int elevatorId, int floor) {
        elevatorController.acceptInternalRequest(floor);
    }
}
