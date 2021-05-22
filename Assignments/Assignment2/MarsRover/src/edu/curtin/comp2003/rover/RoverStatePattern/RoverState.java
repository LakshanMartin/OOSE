package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

/**
 * Interface that implements methods that are dependent of the current State
 * of the Rover
 */
public interface RoverState 
{
    void drive(Rover context, double newDist);
    void turn(Rover context, double newAngle);
    void analyseSoil(Rover context);
}
