package com.example.MarsRoverApp;

import com.example.MarsRoverApp.model.DirectionEnum;
import com.example.MarsRoverApp.model.RoverCommand;
import com.example.MarsRoverApp.model.RoverLocation;
import com.example.MarsRoverApp.service.RoverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Mars Rover application.
 *  It validates the input and processes them to move multiple rovers
 *
 * @author Sapna Agarwal
 */

@SpringBootApplication
@Slf4j
public class MarsRoverAppApplication implements ApplicationRunner {

	private List<RoverLocation> roverLocationList;

	private List<RoverCommand> roverCommandList;

	@Autowired
	private RoverService roverService;

	public static void main(String[] args) {
		SpringApplication.run(MarsRoverAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		validateInput(args.getSourceArgs());
		roverService.performRoverOperation(roverLocationList, roverCommandList);
		displayOutput();
	}

	/**
	 * Validates command line arguments passed by user and processes them
	 * @param inputs Command line arguments passed by user
	 */
	private void validateInput(String[] inputs) {
		if (inputs != null) {
			if (inputs.length % 2 != 0) {  //checks for correct number of arguments. The arguments must be in multiple of 2.
				log.error("Invalid input...incorrect number of arguments");
				System.exit(1);
			}

			roverLocationList = new ArrayList<>();
			roverCommandList = new ArrayList<>();

			for (int i = 0; i < inputs.length; i = i + 2) { // Initial position of rover must have X-coordinate, Y-coordinate and direction
				String[] locationArr = inputs[i].split(",");
				if (locationArr.length != 3) {
					log.error("Invalid location");
					System.exit(1);
				}

				RoverLocation roverLocation = RoverLocation.builder() //Creating Roverlocation object
						.roverId((i / 2) + 1)
						.x(Integer.parseInt(locationArr[0]))
						.y(Integer.parseInt(locationArr[1]))
						.direction(DirectionEnum.valueOf(locationArr[2]))
						.build();


				String commandString = inputs[i + 1];
				List<String> commandList = Arrays.asList(commandString.split(","));  //Storing commands as a list

				RoverCommand command = RoverCommand.builder()
						.commandList(commandList)
						.roverId(roverLocation.getRoverId())
						.build();

				roverCommandList.add(command);
				roverLocationList.add(roverLocation);
			}
		}else{
			log.error("Please provide valid input!!");
		}
	}

	/**
	 * Displays final position of the rovers
	 */
	private void displayOutput() {
		roverLocationList.stream().forEach(r -> {
			log.info("Rover {} Final Coordinate: {}, {}", r.getRoverId(), r.getX(), r.getY());
			log.info("Rover {} Final Direction: {}", r.getRoverId(), r.getDirection().value);
		});
	}
}
