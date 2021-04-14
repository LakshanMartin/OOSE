package org.lucky.electricityNetwork.model.CategoryDecoratorPatt;

public abstract class CategoryDecorator implements PowerCategory
{
    private PowerCategory powCat;

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
