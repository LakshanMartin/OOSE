package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.PowerCategory;

public class PowerUsage implements Node
{
    private String name;
    private String parent;
    private int depth;
    private PowerCategory usage;

    public PowerUsage(String name, String parent, int depth, PowerCategory usage)
    {
        this.name = name;
        this.parent = parent;
        this.depth = depth;
        this.usage = usage;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getParent()
    {
        return parent;
    }

    @Override
    public int getDepth() 
    {
        return depth;
    }

    @Override
    public String getNodeValues() 
    {
        return name + "," + parent + getPowerStr();
    }

    private String getPowerStr() 
    {        
        return usage.getCategory();
    }
}