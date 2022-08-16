package com.example.MarsRoverApp.service;

import com.example.MarsRoverApp.model.DirectionEnum;
import com.example.MarsRoverApp.model.RoverCommand;
import com.example.MarsRoverApp.model.RoverLocation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RoverMoveServiceTest {

    @InjectMocks
    RoverMoveService moveService;

    @Test
    public void testMoveForward_NoCollision() {
        RoverLocation location1 = mockRoverLocation(1,1,2, DirectionEnum.N);
        RoverLocation location2 = mockRoverLocation(2,2,3, DirectionEnum.N);
        List<RoverLocation> locationList = List.of(location1, location2);
        RoverCommand command = RoverCommand.builder().build();
        moveService.moveForward(locationList,location1,command);
        Assert.assertEquals(3,location1.getY());
        Assert.assertFalse(command.isTerminated());
    }

    @Test
    public void testMoveForward_Collision() {
        RoverLocation location1 = mockRoverLocation(1,1,2, DirectionEnum.N);
        RoverLocation location2 = mockRoverLocation(2,1,3, DirectionEnum.E);
        List<RoverLocation> locationList = List.of(location1, location2);
        RoverCommand command = RoverCommand.builder().build();
        moveService.moveForward(locationList,location1,command);
        Assert.assertEquals(2,location1.getY());
        Assert.assertTrue(command.isTerminated());
    }

    @Test
    public void moveBackward_NoCollision() {
        RoverLocation location1 = mockRoverLocation(1,1,2, DirectionEnum.N);
        RoverLocation location2 = mockRoverLocation(2,2,3, DirectionEnum.N);
        List<RoverLocation> locationList = List.of(location1, location2);
        RoverCommand command = RoverCommand.builder().build();
        moveService.moveBackward(locationList,location1,command);
        Assert.assertEquals(1,location1.getY());
        Assert.assertFalse(command.isTerminated());
    }

    @Test
    public void moveBackward_Collision() {
        RoverLocation location1 = mockRoverLocation(1,1,2, DirectionEnum.N);
        RoverLocation location2 = mockRoverLocation(2,1,1, DirectionEnum.N);
        List<RoverLocation> locationList = List.of(location1, location2);
        RoverCommand command = RoverCommand.builder().build();
        moveService.moveBackward(locationList,location1,command);
        Assert.assertEquals(2,location1.getY());
        Assert.assertTrue(command.isTerminated());
    }

    @Test
    public void testRotateClockwise() {
        RoverLocation location = mockRoverLocation(1,1,2, DirectionEnum.N);
        moveService.rotateClockwise(location);
        Assert.assertNotNull(location);
        Assert.assertEquals(location.getDirection().value, "East");
    }

    @Test
    public void testRotateAntiClockWise() {
        RoverLocation location = mockRoverLocation(1,1,2, DirectionEnum.N);
        moveService.rotateAntiClockWise(location);
        Assert.assertNotNull(location);
        Assert.assertEquals(location.getDirection().value, "West");
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