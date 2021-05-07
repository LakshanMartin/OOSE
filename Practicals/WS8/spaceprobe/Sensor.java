package edu.curtin.spaceprobe;

public class Sensor
{
    public String name;
    public boolean operational = true;
 
    public Sensor(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean isOperational()
    {
        return operational;
    }
    
    public void failed()
    {
        operational = false;
    }

    // "Native" method -- implemented in C code.
    public native double takeMeasurement();
}
