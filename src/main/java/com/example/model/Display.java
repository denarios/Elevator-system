package com.example.model;

import com.example.enums.Directions;

public class Display {
    private int floor;
    private Directions direction;
    
    public Display(int floor, Directions direction) {
        this.floor = floor;
        this.direction = direction;
    }
    public void update(int floor, Directions directions){
        this.floor = floor;
        this.direction = directions;
    }
    public int getFloors(){
        return floor;
    }
    public Directions gDirections(){
        return direction;
    }
}
