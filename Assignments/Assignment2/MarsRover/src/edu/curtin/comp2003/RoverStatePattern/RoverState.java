package edu.curtin.comp2003.RoverStatePattern;

import edu.curtin.comp2003.Rover;

/**
 * Interface that implements methods that are dependent of the current State
 * of the Rover
 */
public interface RoverState 
{
    void startDriving(Rover context, double newDist);
    void stopDriving(Rover context);
    void turn(Rover context, double newAngle);
    void analyseSoil(Rover context);
}
