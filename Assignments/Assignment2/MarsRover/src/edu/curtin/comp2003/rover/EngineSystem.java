package edu.curtin.comp2003.rover;

public class EngineSystem 
{
    private int distCount = -1;

    public EngineSystem(){}

    public void startDriving()
    {

    }   
    
    public void stopDriving()
    {

    }

    public void turn(double angle)
    {

    }

    public double getDistanceDriven()
    {
        double[] distList = new double[]{
            1.0, 1.0, 10.0, 15.0, 15.0, 15.0, 15.0, 15.0 , 15.0, 15.0, 15.0
            //0.0, 0.0, 10.0, 5.0, 1.0, 1.0, 1.0, 1.0, 10.0
        };

        if(distCount == distList.length - 1)
        {
            return distList[distList.length - 1];
        }
        else
        {
            distCount++;

            return distList[distCount];
        }
    }
}
