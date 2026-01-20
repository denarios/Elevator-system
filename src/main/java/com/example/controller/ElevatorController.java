package com.example.controller;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.example.enums.Directions;
import com.example.model.Elevator;
import com.example.stretegy.ElevatorMovementStrategy;

public class ElevatorController {

    private final int elevatorId;
    private final Elevator elevator;
    private final ElevatorMovementStrategy strategy;

    // UP → min heap
    private final Queue<Integer> upQueue = new PriorityQueue<>();

    // DOWN → max heap
    private final Queue<Integer> downQueue = new PriorityQueue<>((a, b) -> b - a);

    // Requests that cannot be served in current direction
    private final Queue<Integer> pendingQueue = new LinkedList<>();

    public ElevatorController(
            int elevatorId,
            Elevator elevator,
            ElevatorMovementStrategy strategy) {

        this.elevatorId = elevatorId;
        this.elevator = elevator;
        this.strategy = strategy;
    }

    /* ===================== EXTERNAL REQUEST ===================== */
    // Direction is USER INTENT (UP / DOWN)
    public void acceptExternalRequest(int floor, Directions direction) {

        int current = elevator.getCurrentFloor();
        Directions moving = elevator.getDirection();

        // Elevator IDLE → respect user direction
        if (moving == Directions.IDLE) {
            if (direction == Directions.UP) {
                upQueue.offer(floor);
            } else {
                downQueue.offer(floor);
            }
        }

        // Elevator moving UP
        else if (moving == Directions.UP) {
            if (direction == Directions.UP && floor >= current) {
                upQueue.offer(floor);
            } else {
                pendingQueue.offer(floor);
            }
        }

        // Elevator moving DOWN
        else {
            if (direction == Directions.DOWN && floor <= current) {
                downQueue.offer(floor);
            } else {
                pendingQueue.offer(floor);
            }
        }

        strategy.move(elevator, upQueue, downQueue, pendingQueue);
    }

    /* ===================== INTERNAL REQUEST ===================== */
    // No direction input → follow elevator movement
    public void acceptInternalRequest(int floor) {

        int current = elevator.getCurrentFloor();
        Directions moving = elevator.getDirection();

        // Elevator IDLE → derive direction
        if (moving == Directions.IDLE) {
            if (floor > current) {
                upQueue.offer(floor);
            } else if (floor < current) {
                downQueue.offer(floor);
            }
        }

        // Elevator moving UP
        else if (moving == Directions.UP) {
            if (floor >= current) {
                upQueue.offer(floor);
            } else {
                pendingQueue.offer(floor);
            }
        }

        // Elevator moving DOWN
        else {
            if (floor <= current) {
                downQueue.offer(floor);
            } else {
                pendingQueue.offer(floor);
            }
        }

        strategy.move(elevator, upQueue, downQueue, pendingQueue);
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public int getCurrentFloor() {
        return elevator.getCurrentFloor();
    }

    public Directions getDirection() {
        return elevator.getDirection();
    }

    public int getLastUpRequest() {
        if (upQueue.isEmpty())
            return elevator.getCurrentFloor();
        return ((PriorityQueue<Integer>) upQueue).peek(); // min-heap
    }

    public int getLastDownRequest() {
        if (downQueue.isEmpty())
            return elevator.getCurrentFloor();
        return ((PriorityQueue<Integer>) downQueue).peek(); // max-heap
    }

    public boolean hasPending() {
        return !pendingQueue.isEmpty();
    }

}
