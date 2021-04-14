package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

public class SubCityNode implements Node
{
    private String name;
    private String parent;
    private int depth;

    public SubCityNode(String name, String parent, int depth)
    {
        this.name = name;
        this.parent = parent;
        this.depth = depth;
    }

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
}
