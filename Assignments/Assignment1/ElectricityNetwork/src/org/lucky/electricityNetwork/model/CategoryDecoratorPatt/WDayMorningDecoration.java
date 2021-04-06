package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class WDayMorningDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WDayMorningDecoration(Double usage)
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
        return ", dm=" + usage;
    }
}
