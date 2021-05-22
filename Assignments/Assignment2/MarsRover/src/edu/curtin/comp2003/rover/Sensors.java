package edu.curtin.comp2003.rover;

public class Sensors 
{
    public Sensors(){}

    public double readTemperature()
    {
        return 10.0;
    }    

    public double readVisibility()
    {
        return 10.0;
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
