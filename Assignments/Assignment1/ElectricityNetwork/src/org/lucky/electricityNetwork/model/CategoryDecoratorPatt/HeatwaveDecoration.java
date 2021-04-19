package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

/**
 * This is the Heat Wave decoration of the Decorator Design Pattern
 * implementation of the Power Category class.
 */
public class HeatwaveDecoration extends CategoryDecorator
{
    private Double usage;
    
    //CONSTRUCTOR
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
