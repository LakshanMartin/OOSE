package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

/**
 * This is the Weekday Morning decoration of the Decorator Design Pattern
 * implementation of the Power Category class.
 */
public class WDayMorningDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WDayMorningDecoration(PowerCategory powCat, Double usage)
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
        return ",dm=" + usage;
    }
}
