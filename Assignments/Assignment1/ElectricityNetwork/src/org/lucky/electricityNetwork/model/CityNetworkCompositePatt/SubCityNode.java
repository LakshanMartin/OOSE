package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

public class SubCityNode implements Node
{
    private String name;
    private String parent;

    public SubCityNode(String name, String parent)
    {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String getNodeValues() 
    {
        return name + "," + parent;
    }
}
