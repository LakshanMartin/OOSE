package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

/**
 * This is the Base Component of the Composite Design Pattern. It is the
 * interface class to be implemented by the leaf components.
 */
public interface Node 
{
    public String getName();

    public String getParent();

    public int getDepth();

    public String getNodeValues();

    public Node findNode(String name);

    public boolean isLeaf();
}
