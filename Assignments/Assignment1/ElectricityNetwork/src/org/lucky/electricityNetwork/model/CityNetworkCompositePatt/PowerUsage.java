package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.PowerCategory;

/**
 * This is a leaf component class as a part of the Composite Design Pattern.
 * It contains all the data relevent to the leaf nodes of the City Network
 * tree, specifically the power consumption categories and values.
 */
public class PowerUsage implements Node
{
    //CLASS FIELDS
    private String name;
    private String parent;
    private int depth;
    private PowerCategory usage;

    //CONSTRUCTOR
    public PowerUsage(String name, String parent, int depth, PowerCategory usage)
    {
        this.name = name;
        this.parent = parent;
        this.depth = depth;
        this.usage = usage;
    }

    //ACCESSORS
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getParent()
    {
        return parent;
    }

    @Override
    public int getDepth() 
    {
        return depth;
    }

    @Override
    public String getNodeValues() 
    {
        return name + "," + parent + getPowerStr();
    }

    //SUPPORTING METHODS ------------------------------------------------------
    /**
     * Find and return specific node by name
     * @param name
     * @return
     * REFERENCE: Cooper, David. Lecture 4: Object Relationships. Slide 42.
     */
    @Override
    public Node findNode(String name)
    {
        Node found = null;

        if(this.name.equals(name))
        {
            found = this;
        }

        return found;
    }

    @Override
    public boolean isLeaf()
    {
        return true;
    }

    /**
     * Retrieve the string value of the power consumption values.
     * @return
     */
    private String getPowerStr() 
    {        
        return usage.getCategory();
    }
}
