package com.example.stretegy;

import java.util.Queue;

import com.example.enums.Directions;
import com.example.model.Elevator;

public class LookStrategy implements ElevatorMovementStrategy{
    
    @Override
    public void move( Elevator elevator,Queue<Integer> upQueue, Queue<Integer> downQueue,Queue<Integer> pendingQueue) {
        if (elevator.getDirection() == Directions.IDLE) {
            if (!upQueue.isEmpty()) {
                elevator.setDirection(Directions.UP);
            } else if (!downQueue.isEmpty()) {
                elevator.setDirection(Directions.DOWN);
            } else {
                return; // nothing to do
            }
        }
        if(elevator.getDirection() == Directions.UP){
            serveUp(elevator, upQueue, pendingQueue);
            elevator.setDirection(Directions.DOWN);
            // if pending not empty then move it into upQueye
            movePending(elevator, pendingQueue, upQueue);
            serveDown(elevator, downQueue, pendingQueue);
        }else{
            serveDown(elevator, downQueue, pendingQueue);
            elevator.setDirection(Directions.UP);
            movePending(elevator, pendingQueue, downQueue);
            serveUp(elevator, upQueue, pendingQueue);
        }
    }

    void serveUp(Elevator elevator,Queue<Integer> upQueue,Queue<Integer> pendingQueue){
        while (!upQueue.isEmpty()) {
            int target = upQueue.poll();

            // Cannot serve now → pending
            if (target < elevator.getCurrentFloor()) {
                pendingQueue.offer(target);
                continue;
            }

            while (elevator.getCurrentFloor() < target) {
                elevator.moveUp();
            }
            elevator.stop();
        }
    }
    void serveDown(Elevator elevator,Queue<Integer> downQueue,Queue<Integer> pendingQueue){
        while (!downQueue.isEmpty()) {
            int target = downQueue.poll();

            // Cannot serve now → pending
            if (target > elevator.getCurrentFloor()) {
                pendingQueue.offer(target);
                continue;
            }

            while (elevator.getCurrentFloor() > target) {
                elevator.moveDown();
            }
            elevator.stop();
        }
    }
    void movePending(Elevator elevator, Queue<Integer> pendingQueue, Queue<Integer> activeQueue){
         while (!pendingQueue.isEmpty()) {
            activeQueue.add(pendingQueue.peek());
            pendingQueue.poll();
         }
    }
}
