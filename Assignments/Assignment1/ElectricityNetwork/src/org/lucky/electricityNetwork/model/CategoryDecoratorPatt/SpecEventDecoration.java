package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class SpecEventDecoration extends CategoryDecorator
{
    private Double usage;
    
    public SpecEventDecoration(PowerCategory powCat, Double usage)
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
        return ",s=" + usage;
    }     
}
