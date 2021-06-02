package edu.curtin.comp2003.VisibilityStatePattern;

import edu.curtin.comp2003.Rover;

/**
 * Interface that implements methods that are dependent of the current
 * visibility State of the Rover
 */
public interface VisibilityState 
{
    void belowFourKM(Rover context, double temp, double vis, double light);
    void normal(Rover context, double temp, double vis, double light);
    void aboveFiveKM(Rover context, double temp, double vis, double light);
}
