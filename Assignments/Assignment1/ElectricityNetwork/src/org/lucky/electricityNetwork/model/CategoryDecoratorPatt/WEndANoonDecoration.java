package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class WEndANoonDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WEndANoonDecoration(Double usage)
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
        return ", ea=" + usage;
    }     
}
