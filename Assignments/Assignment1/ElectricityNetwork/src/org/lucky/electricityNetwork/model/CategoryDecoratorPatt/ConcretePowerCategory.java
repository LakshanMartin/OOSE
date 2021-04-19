package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

/**
 * This is the Concrete class of the Decorator Design Pattern implementation of
 * the PowerCategory class
 */
public class ConcretePowerCategory implements PowerCategory
{
    //EMPTY CONSTRUCTOR
    public ConcretePowerCategory(){}

    @Override
    public String getCategory() 
    {
        return "";
    } 
}
