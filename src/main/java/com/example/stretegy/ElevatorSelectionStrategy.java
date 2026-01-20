package com.example.stretegy;

import java.util.List;

import com.example.controller.ElevatorController;
import com.example.enums.Directions;

public interface ElevatorSelectionStrategy {

    public ElevatorController selectElevator( List< ElevatorController> elevators, int floor, Directions direction);
}
