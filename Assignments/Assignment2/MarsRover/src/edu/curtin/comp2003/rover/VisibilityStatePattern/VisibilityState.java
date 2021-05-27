package edu.curtin.comp2003.rover.VisibilityStatePattern;

import edu.curtin.comp2003.rover.Rover;

public interface VisibilityState 
{
    void belowFourKM(Rover context, double temp, double vis, double light);
    void normal(Rover context, double temp, double vis, double light);
    void aboveFiveKM(Rover context, double temp, double vis, double light);
}
