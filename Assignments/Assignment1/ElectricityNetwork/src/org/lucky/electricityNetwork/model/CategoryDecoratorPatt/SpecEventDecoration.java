package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public class SpecEventDecoration extends CategoryDecorator
{
    private Double usage;
    
    public SpecEventDecoration(Double usage)
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
        return ",s=" + usage;
    }     
}
