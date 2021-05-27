package edu.curtin.comp2003.rover;

public class Sensors 
{
    private int tempCount = -1;
    private int visCount = -1;
    private int lightCount = -1;
    private int photoCount = -1;

    public Sensors(){}

    public double readTemperature()
    {
        return 10.0;
    }    

    public double readVisibility()
    {
        double[] visList = new double[]{
            4.0, 4.1, 4.2, 4.3, 4.4, 4.5
            //5.1, 6.0, 4.1, 4.5, 2.0, 4.0, 4.2, 4.3, 4.5, 5.7, 4.3, 3.0
        };

        if(visCount == visList.length - 1)
        {
            return visList[visList.length - 1];
        }
        else
        {
            visCount++;

            return visList[visCount];
        }
    }

    public double readLightLevel()
    {
        return 10.0;
    }

    public byte[] takePhoto()
    {
        return new byte[]{72, 101, 108, 108, 111};
    }
}
