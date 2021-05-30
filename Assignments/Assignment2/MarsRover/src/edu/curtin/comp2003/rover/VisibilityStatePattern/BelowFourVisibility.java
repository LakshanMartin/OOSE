package edu.curtin.comp2003.rover.VisibilityStatePattern;

import edu.curtin.comp2003.rover.Rover;

/**
 * This class implements the VisibilityState interface and represents the 
 * visibility state the Rover is in while visibility is below 4 km.
 */
public class BelowFourVisibility implements VisibilityState
{
    @Override
    public void belowFourKM(Rover context, double temp, double vis, double light) 
    {
        //Do nothing
    }

    @Override
    public void normal(Rover context, double temp, double vis, double light) 
    {
        context.sendMessage("E " + temp + " " + vis + " " + light + "\n");
        context.setVisibilityState(new NormalVisibility());      
    }

    @Override
    public void aboveFiveKM(Rover context, double temp, double vis, double light) 
    {
        context.sendMessage("E " + temp + " " + vis + " " + light + "\n");
        context.setVisibilityState(new AboveFiveVisibility());
    }
}
