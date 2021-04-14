package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class WEndEveningDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WEndEveningDecoration(PowerCategory powCat, Double usage)
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
        return ",ee=" + usage;
    }     
}
