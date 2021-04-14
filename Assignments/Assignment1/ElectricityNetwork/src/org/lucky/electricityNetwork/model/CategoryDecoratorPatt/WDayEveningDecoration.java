package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class WDayEveningDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WDayEveningDecoration(PowerCategory powCat, Double usage)
    {
        super(powCat);
        this.usage = usage;
    }

    @Override
    public String getCategory()
    {
        return super.getCategory() + addUsage();
    }

    private String addUsage()
    {
        return ",de=" + usage;
    }     
}
