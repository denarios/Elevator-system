package com.example.stretegy;

import java.util.List;

import com.example.controller.ElevatorController;
import com.example.enums.Directions;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public ElevatorController selectElevator(
            List<ElevatorController> elevators,
            int floor,
            Directions direction) {

        ElevatorController best = null;
        int minCost = Integer.MAX_VALUE;

        for (ElevatorController ec : elevators) {
            int cost = calculateCost(ec, floor, direction);
            System.out.println("elevator Id : " +  ec.getElevatorId() + "Prinitng coast" +cost);
            if (cost < minCost) {
                minCost = cost;
                best = ec;
            }
        }
        return best;
    }

    private int calculateCost(
            ElevatorController ec,
            int requestFloor,
            Directions requestDir) {

        int current = ec.getElevator().getCurrentFloor();
        Directions moving = ec.getElevator().getDirection();

        int upEnd = ec.getLastUpRequest();
        int downEnd = ec.getLastDownRequest();

        // IDLE
        if (moving == Directions.IDLE) {
            return Math.abs(current - requestFloor);
        }

        // MOVING UP
        if (moving == Directions.UP) {

            // Can serve directly
            if (requestDir == Directions.UP && requestFloor >= current) {
                return requestFloor - current;
            }

            // Must defer
            return (upEnd - current)           // finish UP
                 + (upEnd - downEnd)           // go DOWN
                 + Math.abs(requestFloor - downEnd);
        }

        // MOVING DOWN
        if (requestDir == Directions.DOWN && requestFloor <= current) {
            return current - requestFloor;
        }

        return (current - downEnd)             // finish DOWN
             + (upEnd - downEnd)               // go UP
             + Math.abs(requestFloor - upEnd);
    }
}
