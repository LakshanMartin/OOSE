package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

/**
 * This is the Weekday Afternoon decoration of the Decorator Design Pattern
 * implementation of the Power Category class.
 */
public class WDayANoonDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WDayANoonDecoration(PowerCategory powCat, Double usage)
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
        return ",da=" + usage;
    } 
}
