package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

/**
 * This is the abstract Decorator class for the Decorator Design Pattern implementation
 * of the PowerCategory class
 */
public abstract class CategoryDecorator implements PowerCategory
{
    private PowerCategory powCat;

    //CONSTRUCTOR
    public CategoryDecorator(PowerCategory powCat)
    {
        this.powCat = powCat;
    }

    @Override
    public String getCategory()
    {
        return powCat.getCategory();
    }   
}
