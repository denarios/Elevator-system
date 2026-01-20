package com.example;

import java.util.List;

import com.example.controller.*;
import com.example.enums.Directions;
import com.example.model.Elevator;
import com.example.model.Externalbutton;
import com.example.stretegy.*;

public class Main {

    public static void main(String[] args) {

        ElevatorMovementStrategy movementStrategy = new LookStrategy();

        /* ================== ELEVATOR 1 ================== */
        Elevator elevator1 = new Elevator(1, 0, null);
        ElevatorController controller1 =
                new ElevatorController(1, elevator1, movementStrategy);
        InternalButtonController internal1 =
                new InternalButtonController(controller1);

        // wire internal controller ONCE
        elevator1 = new Elevator(1, 0, internal1);

        /* ================== ELEVATOR 2 ================== */
        Elevator elevator2 = new Elevator(2, 6, null);
        ElevatorController controller2 =
                new ElevatorController(2, elevator2, movementStrategy);
        InternalButtonController internal2 =
                new InternalButtonController(controller2);

        elevator2 = new Elevator(2, 6, internal2);

        /* ================== SELECTION ================== */
        ElevatorSelectionStrategy selectionStrategy =
                new NearestElevatorStrategy();

        ExternalController externalController =
                new ExternalController(
                        List.of(controller1, controller2),
                        selectionStrategy
                );

        Externalbutton externalButton =
                new Externalbutton(externalController);

        /* ================== DEMO ================== */

        System.out.println("---- EXTERNAL REQUESTS ----");
        externalButton.press(4, Directions.UP);
        externalButton.press(7, Directions.DOWN);

        System.out.println("\n---- INTERNAL REQUESTS ----");
        elevator1.press(6);
        elevator2.press(2);
    }
}
