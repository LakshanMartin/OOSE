package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class WEndMorningDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WEndMorningDecoration(PowerCategory powCat, Double usage)
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
        return ",em=" + usage;
    }
}
