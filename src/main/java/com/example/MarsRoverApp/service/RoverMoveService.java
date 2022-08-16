package com.example.MarsRoverApp.service;

import com.example.MarsRoverApp.model.RoverCommand;
import com.example.MarsRoverApp.model.RoverLocation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Performs rover movement depending upon the commands.
 * It also check for collision before making change.
 *
 * @author Sapna Agarwal
 */
@Service
public class RoverMoveService {

    /**
     * Moves rover forward and changes coordinate depending upon the direction.
     * Before making change it checks for collision.
     * If making change results in collision then the change is not made and
     * rest of the commands for the rover are cancelled by setting isTerminated flag for the RoverCommand object.
     *
     * @param locationList list of rovers location
     * @param currentLocation current rover location
     * @param command rover command
     */
    public void moveForward(List<RoverLocation> locationList, RoverLocation currentLocation, RoverCommand command) {
        boolean isColliding = false;
        switch (currentLocation.getDirection()){
            case N:
                //Checking for collision
                isColliding = checkForCollision(locationList,currentLocation.getX(),currentLocation.getY()+1);
                if(!isColliding)
                    currentLocation.incrementY();
                break;

            case S:
                isColliding = checkForCollision(locationList,currentLocation.getX(),currentLocation.getY()-1);
                if(!isColliding)
                    currentLocation.decrementY();
                break;

            case E:
                isColliding = checkForCollision(locationList,currentLocation.getX()+1, currentLocation.getY());
                if(!isColliding)
                    currentLocation.incrementX();
                break;

            case W:
                isColliding = checkForCollision(locationList,currentLocation.getX()-1, currentLocation.getY());
                if(!isColliding)
                    currentLocation.decrementX();
                break;
        }
        //Setting flag to true for collisions, so that rest of commands are not executed
        if(isColliding){
            command.setTerminated(true);
        }
    }

    /**
     * Moves rover backwards and changes coordinate depending upon the direction.
     * Before making change it checks for collision.
     * If making change results in collision then the change is not made and
     * rest of the commands for the rover are cancelled by setting isTerminated flag for the RoverCommand object.
     *
     * @param locationList list of rovers location
     * @param currentLocation current rover location
     * @param command rover command
     */
    public void moveBackward(List<RoverLocation> locationList, RoverLocation currentLocation, RoverCommand command) {
        boolean isColliding = false;
        switch (currentLocation.getDirection()){
            case N:
                isColliding = checkForCollision(locationList, currentLocation.getX(), currentLocation.getY()-1);
                if(!isColliding)
                    currentLocation.decrementY();
                break;
            case S:
                isColliding = checkForCollision(locationList, currentLocation.getX(), currentLocation.getY()+1);
                if(!isColliding)
                    currentLocation.incrementY();
                break;
            case E:
                isColliding = checkForCollision(locationList,currentLocation.getX()-1, currentLocation.getY());
                if(!isColliding)
                    currentLocation.decrementX();
                break;
            case W:
                isColliding = checkForCollision(locationList,currentLocation.getX()+1, currentLocation.getY());
                if(!isColliding)
                    currentLocation.incrementX();
                break;
        }
        //Setting flag to true for collisions, so that rest of commands are not executed
        if(isColliding){
            command.setTerminated(true);
        }
    }

    /**
     * Changes the direction of rover to 90 degrees clockwise
     * @param location rover location
     */
    public void rotateClockwise(RoverLocation location) {
        switch (location.getDirection()){
            case N:
                location.setDirection("E");
                break;
            case S:
                location.setDirection("W");
                break;
            case E:
                location.setDirection("S");
                break;
            case W:
                location.setDirection("N");
                break;
        }
    }

    /**
     * Changes the direction of rover to 90 degrees anticlockwise
     * @param location rover location
     */
    public void rotateAntiClockWise(RoverLocation location) {
        switch (location.getDirection()){
            case N:
                location.setDirection("W");
                break;
            case S:
                location.setDirection("E");
                break;
            case E:
                location.setDirection("N");
                break;
            case W:
                location.setDirection("S");
                break;
        }
    }

    /**
     * Check if a command might result into collision
     * @param locationList List of rovers location
     * @param xValue X coordinate
     * @param yValue Y coordinate
     * @return true if there is a collision
     */
    private boolean checkForCollision(List<RoverLocation> locationList, int xValue, int yValue){
        long count = locationList
                .stream()
                .filter(l -> l.getX() == xValue && l.getY() == yValue)
                .count();
        if(count > 0)
            return true;
        return false;
    }
}
