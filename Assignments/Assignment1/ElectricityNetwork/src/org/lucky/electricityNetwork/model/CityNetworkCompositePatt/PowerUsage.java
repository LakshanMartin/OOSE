package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.PowerCategory;

public class PowerUsage implements Node
{
    private String name;
    private String parent;
    private PowerCategory usage;

    public PowerUsage(String name, String parent, PowerCategory usage)
    {
        this.name = name;
        this.parent = parent;
        this.usage = usage;
    }

    @Override
    public String getNodeValues() 
    {
        return name + "," + parent + getPower();
    }

    private String getPower() 
    {        
        return usage.getCategory();
    }
}
