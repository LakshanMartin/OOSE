package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class HeatwaveDecoration extends CategoryDecorator
{
    private Double usage;
    
    public HeatwaveDecoration(Double usage)
    {
        this.usage = usage;
    }

    @Override
    public String getCategory()
    {
        return super.getCategory() + addUsage();
    }

    private String addUsage()
    {
        return ", h=" + usage;
    }     
}
