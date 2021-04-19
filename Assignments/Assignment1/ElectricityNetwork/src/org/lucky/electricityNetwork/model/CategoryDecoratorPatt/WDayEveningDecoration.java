package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

/**
 * This is the Weekday Evening decoration of the Decorator Design Pattern
 * implementation of the Power Category class.
 */
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
