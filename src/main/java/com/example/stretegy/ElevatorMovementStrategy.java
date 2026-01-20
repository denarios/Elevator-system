package com.example.stretegy;

import java.util.Queue;

import com.example.model.Elevator;

public interface ElevatorMovementStrategy {

    void move(
        Elevator elevator,
        Queue<Integer> upQueue,
        Queue<Integer> downQueue,
        Queue<Integer> pendingQueue
    );
    
}