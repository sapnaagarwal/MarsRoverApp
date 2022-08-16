package com.example.MarsRoverApp.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;


/**
 * Data structure to store rover commands
 *
 * @author Sapna Agarwal
 */
@Data
@Builder
public class RoverCommand {

    private int roverId;

    private List<String> commandList;

    private int index; //it is to keep track of number of commands executed

    private boolean completed; //it is set to true if all the commands for the rover are executed

    private boolean terminated; //it is to true if a step results into collision

    public void incrementIndex() {
        index++;
    }

}
