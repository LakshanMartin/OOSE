package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class WEndMorningDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WEndMorningDecoration(Double usage)
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
        return ",em=" + usage;
    }
}
