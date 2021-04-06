package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class WEndEveningDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WEndEveningDecoration(Double usage)
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
        return ",ee=" + usage;
    }     
}
