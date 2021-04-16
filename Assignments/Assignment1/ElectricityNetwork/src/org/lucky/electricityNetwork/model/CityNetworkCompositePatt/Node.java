package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

public interface Node 
{
    public String getName();

    public String getParent();

    public int getDepth();

    public String getNodeValues();

    public Node findNode(String name);

    public boolean isLeaf();
}
