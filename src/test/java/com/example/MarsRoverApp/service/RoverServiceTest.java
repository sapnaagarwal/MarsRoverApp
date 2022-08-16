package com.example.MarsRoverApp.service;

import com.example.MarsRoverApp.model.DirectionEnum;
import com.example.MarsRoverApp.model.RoverCommand;
import com.example.MarsRoverApp.model.RoverLocation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoverServiceTest {

    @Mock
    RoverMoveService moveService;

    @InjectMocks
    RoverService roverService;

    @Test
    public void testPerformRoverOperation() {
        RoverLocation location1 = mockRoverLocation(1,1,2, DirectionEnum.N);
        RoverLocation location2 = mockRoverLocation(2,4,8, DirectionEnum.E);
        List<RoverLocation> locationList = List.of(location1, location2);
        RoverCommand command1 = RoverCommand.builder()
                .commandList(List.of("f","l"))
                .roverId(1)
                .build();
        RoverCommand command2 = RoverCommand.builder()
                .commandList(List.of("b","r"))
                .roverId(2)
                .build();
        List<RoverCommand> commandList = List.of(command1, command2);

        roverService.performRoverOperation(locationList, commandList);

        verify(moveService, times(1)).moveForward(anyList(), any(),any());
        verify(moveService, times(1)).moveBackward(anyList(), any(),any());
        verify(moveService, times(1)).rotateClockwise(any());
        verify(moveService, times(1)).rotateAntiClockWise(any());

        Assert.assertTrue(command1.isCompleted());
        Assert.assertFalse(command1.isTerminated());

        Assert.assertTrue(command2.isCompleted());
        Assert.assertFalse(command2.isTerminated());
    }

    private RoverLocation mockRoverLocation(int id, int x, int y, DirectionEnum dir) {
        RoverLocation location = RoverLocation.builder()
                .roverId(id)
                .x(x)
                .y(y)
                .direction(dir)
                .build();
        return location;
    }
}