package com.example.controller;

import java.util.List;

import com.example.enums.Directions;
import com.example.model.Elevator;
import com.example.stretegy.ElevatorSelectionStrategy;

public class ExternalController {
   private final List<ElevatorController> elevatorControllers;
   private final ElevatorSelectionStrategy strategy;

   public ExternalController(
            List<ElevatorController> elevatorControllers,
            ElevatorSelectionStrategy strategy) {
        this.elevatorControllers = elevatorControllers;
        this.strategy = strategy;
    }

    public void submitRequest(int floor, Directions direction) {
        ElevatorController chosen = strategy.selectElevator(elevatorControllers,floor,direction);
         System.out.println(
            "External request at floor " + floor +
            " direction " + direction +
            " → Selected Elevator " + chosen.getElevatorId()
        );
        chosen.acceptExternalRequest(floor,direction);
    }
    
    
}
