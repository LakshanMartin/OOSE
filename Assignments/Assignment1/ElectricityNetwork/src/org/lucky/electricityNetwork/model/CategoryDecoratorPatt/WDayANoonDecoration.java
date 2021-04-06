package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class WDayANoonDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WDayANoonDecoration(Double usage)
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
        return ",da=" + usage;
    } 
}
