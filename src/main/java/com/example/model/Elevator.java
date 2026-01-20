package com.example.model;

import com.example.controller.InternalButtonController;
import com.example.enums.Directions;
import com.example.enums.ElevatorStatus;

public class Elevator {
    private final int Id;
    private Directions dir;
    private ElevatorStatus status;
    private int currentfloor;
    private final Internalbutton button;
    private final Display display;

    public Elevator(int id, int currentfloor, InternalButtonController controller) {
        Id = id;
        this.currentfloor = currentfloor;
        dir = Directions.IDLE;
        status = ElevatorStatus.STOPPED;
        display = new Display(currentfloor, dir);
        this.button = new Internalbutton(Id, controller);
    }

    public void moveUp() {
        currentfloor++;
        dir = Directions.UP;
        status = ElevatorStatus.MOVING;
        display.update(currentfloor, dir);
        System.out.println("Elevator " + Id + " moving UP → floor " + currentfloor);
    }

    // Move ONE FLOOR DOWN
    public void moveDown() {
        currentfloor--;
        dir = Directions.DOWN;
        status = ElevatorStatus.MOVING;
        display.update(currentfloor, dir);
         System.out.println("Elevator " + Id + " moving DOWN → floor " + currentfloor);
    }

    public void stop() {
        status = ElevatorStatus.STOPPED;
        display.update(currentfloor, dir);
        System.out.println("Elevator " + Id + " STOPPED at floor " + currentfloor);
    }

    public int getCurrentFloor() {
        return currentfloor;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setDirection(Directions dir) {
        this.dir = dir;
    }

    public Directions getDirection() {
        return dir;
    }

    public void press(int tofloor) {
        button.press(tofloor);
    }
}
