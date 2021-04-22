package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

/**
 * This is a child component class as a part of the Composite Design Pattern.
 * It contains all the data relevent to the sub nodes of the City Network
 * tree, specifically the node name and the node's parent name.
 */
public class SubCityNode implements Node
{
    //CLASS FIELDS
    private String name;
    private String parent;
    private int depth;

    //CONSTRUCTOR
    public SubCityNode(String name, String parent, int depth)
    {
        this.name = name;
        this.parent = parent;
        this.depth = depth;
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
        return name + "," + parent;
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
        return false;
    }
}
