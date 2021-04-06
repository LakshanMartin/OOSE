package org.lucky.electricityNetwork.model;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.PowerCategory;

public class PowerUsage implements PowerNode
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
    public String getPower() 
    {        
        return usage.getCategory();
    }
}
