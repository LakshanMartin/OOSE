package edu.curtin.spaceprobe;

public class FuelAmount
{
    private double oxygen;
    private double hydrogen;

    public FuelAmount(double oxygen, double hydrogen)
    {
        this.oxygen = oxygen;
        this.hydrogen = hydrogen;
    }

    public double getOxygen() 
    { 
        return oxygen; 
    }
    
    public double getHydrogen() 
    {   
        return hydrogen; 
    }
}
