package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class HeatwaveDecoration extends CategoryDecorator
{
    private Double usage;
    
    public HeatwaveDecoration(PowerCategory powCat, Double usage)
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
        return ",h=" + usage;
    }     
}
