package com.example.MarsRoverApp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Data structure to store rover location information
 *
 * @author Sapna Agarwal
 */
@Getter
@Builder
@ToString
@NonNull
public class RoverLocation {

    private int roverId;

    private int x;

    private int y;

    private DirectionEnum direction;

    public void incrementX(){
        x++;
    }

    public int incrementY(){
        return y++;
    }

    public int decrementX(){
        return x--;
    }

    public int decrementY(){
        return y--;
    }

    public void setDirection(String direction){
        this.direction = DirectionEnum.valueOf(direction);
    }
}
