# Mars Rover 
The Mars rover is controlled by sending it a stream of commands. It reacts according to the received commands in sequence. <br />
One command is represented by 1 character. <br />
Stream of commands is presented by an array of character.

### Table of commands:
* f – Move forward 1 coordinate in the current direction
* b – Move backward 1 coordinate in the current direction
* r – Rotate 90 degree clock-wise from current direction
* l – Rotate 90 degree anti clock-wise from current direction

 ### Implementation
 The program is designed to handle multiple rovers through CLI. <br/>
 The arguments passed will be in the format "X-coordinate, Y-coordinate, Dir" "<stream of comands>" "X-coordinate, Y-coordinate, Dir" "<stream of comands>" ...
 <br /> The way program works is
 1.  Each rover gets a turn to execute a command.
 2.  Before executing command, it is checked for collision.
 3.  If collision occurs then rover will not proceed and all the commands will be cancelled for that rover.
 4.  We will continue with other rovers and keep on executing steps 2 -3 or until commands are finished for the rover.


 #### Example of interaction with the command through parameters:
  ` java -jar target/MarsRoverApp.jar "3,4,N" "f,f,r,f,f" "4,5,N" "f,f"` 

 #### Output on the console:
` Rover 1 Final Coordinate: 5, 6` <br />
` Rover 1 Final Direction: East`<br />
` Rover 2 Final Coordinate: 4, 7` <br />
` Rover 2 Final Direction: North`<br />




