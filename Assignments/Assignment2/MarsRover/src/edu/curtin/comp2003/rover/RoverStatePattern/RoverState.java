package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

public interface RoverState 
{
    void drive(Rover context);
    void turn(Rover context);
    void takePhoto(Rover context);
    void reportEnvironment(Rover context);
    void analyseSoil(Rover context);
}
