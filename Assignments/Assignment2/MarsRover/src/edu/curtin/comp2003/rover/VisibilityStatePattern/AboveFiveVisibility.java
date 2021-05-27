package edu.curtin.comp2003.rover.VisibilityStatePattern;

import edu.curtin.comp2003.rover.Rover;

public class AboveFiveVisibility implements VisibilityState
{
    @Override
    public void belowFourKM(Rover context, double temp, double vis, double light) 
    {
        context.sendMessage("E " + temp + " " + vis + " " + light + "\n");
        context.setVisibilityState(new BelowFourVisibility());
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
        //Do nothing        
    }
}
