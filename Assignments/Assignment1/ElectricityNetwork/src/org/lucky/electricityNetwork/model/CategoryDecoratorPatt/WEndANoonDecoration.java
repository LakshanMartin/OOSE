package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

/**
 * This is the Weekend Afternoon decoration of the Decorator Design Pattern
 * implementation of the Power Category class.
 */
public class WEndANoonDecoration extends CategoryDecorator
{
    private Double usage;
    
    public WEndANoonDecoration(PowerCategory powCat, Double usage)
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
        return ",ea=" + usage;
    }     
}
