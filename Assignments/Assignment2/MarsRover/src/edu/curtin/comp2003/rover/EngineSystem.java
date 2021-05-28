package edu.curtin.comp2003.rover;

public class EngineSystem 
{
    private boolean driving = false;
    private double dist = 0.0;

    public EngineSystem(){}

    public void startDriving()
    {
        driving = true;
    }   
    
    public void stopDriving()
    {
        driving = false;
    }

    public void turn(double angle)
    {

    }

    public double getDistanceDriven()
    {
        if(driving)
        {
            //dist++;
            dist += 2.0;
        }

        return dist;
    }
}
