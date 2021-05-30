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
            dist += 0.1;
            System.out.println("\nODOMETER: " + dist + "\n");
        }

        return dist;
    }
}
