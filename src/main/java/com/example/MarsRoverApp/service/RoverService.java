package com.example.MarsRoverApp.service;

import com.example.MarsRoverApp.model.RoverCommand;
import com.example.MarsRoverApp.model.RoverLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Rover service
 * @author Sapna Agarwal
 */
@Service
public class RoverService {

    @Autowired
    private RoverMoveService moveService;

    /**
     * Evaluates the changes to be performed on rover
     * @param locationList
     * @param commandList
     */
    public void performRoverOperation(List<RoverLocation> locationList, List<RoverCommand> commandList){
        int i=0;
        int commandDoneCount = 0;
        // one command per rover is executed in sequential order
        do{
            if(i >= locationList.size()){ //if i reaches the size of rover list then it is again set to 0,
                i=0;                        // so that we can start to execute the command from the first rover
                commandDoneCount = 0;
            }

            RoverCommand currentCommand = commandList.get(i);

            if(currentCommand.getIndex() == currentCommand.getCommandList().size()){ //if index is equal to total number of commands for a rover
                currentCommand.setCompleted(true);                                    //then we set completed to true
            }

            if(!currentCommand.isTerminated() && !currentCommand.isCompleted()) {
                performAction(locationList, locationList.get(i), currentCommand); //process command for rover when it's not terminated/completed
                currentCommand.incrementIndex(); //increasing the index of the command, so following command will be executed next time
            }else {
                commandDoneCount++;
            }

            i++;

        }while(commandDoneCount < locationList.size()); // exit loop when manoeuvre for all the rovers is done

    }

    /**
     * Executes a command for a rover
     * @param locationList  rover location list
     * @param currentRoverLocation current rover location
     * @param roverCommand rover command
     */
    private void performAction(List<RoverLocation> locationList, RoverLocation currentRoverLocation, RoverCommand roverCommand){
        String command = roverCommand.getCommandList().get(roverCommand.getIndex());

        switch(command) {
            case "f" :
                moveService.moveForward(locationList, currentRoverLocation, roverCommand);
                break;
            case "b" :
                moveService.moveBackward(locationList, currentRoverLocation, roverCommand);
                break;
            case "r" :
                moveService.rotateClockwise(currentRoverLocation);
                break;
            case "l" :
                moveService.rotateAntiClockWise(currentRoverLocation);
                break;
        }
    }
}
