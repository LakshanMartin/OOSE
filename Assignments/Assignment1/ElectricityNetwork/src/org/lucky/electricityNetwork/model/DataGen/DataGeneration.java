package org.lucky.electricityNetwork.model.DataGen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.PowerCategory;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.*;
import org.lucky.electricityNetwork.controller.CityNodeController;

/**
 * This class is responsible for the generation of data to build the City
 * Network tree.
 */
public class DataGeneration 
{
    //CLASS FIELDS
    private String root;
    private int treeDepth;
    private CityNode cityNetwork;
    private CityNodeController cont;
    private DataPool data;

    //CONSTRUCTOR
    public DataGeneration(String root)
    {
        this.root = root;
        this.treeDepth = genRandInt(1, 5); //Random tree depth [1-5]
        cityNetwork = new CityNode(root);
        cont = new CityNodeController();
        data = new DataPool();
    }    

    //ACCESSORS
    public CityNode getCityNetwork()
    {
        return cityNetwork;
    }

    /*************************************************************************/
    //SUPPORTING METHODS

    /**
     * Method used to generate a random int within a range specified
     * @param min
     * @param max
     * @return 
     */
    private int genRandInt(int min, int max)
    {
        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * This method will build the tree 
     */
    public void buildTree()
    {
        int numChild;
        List<String> names;
        String[] newParents, oldParents; 

        //SETUP REQUIRED TO ADD NODES
        numChild = genRandInt(2, 5); //Randomly generate number of child nodes to create
        names = data.getDepthTwo(); //Retrieve list of names specific to tree depth
        oldParents = new String[numChild]; //Initialise array to store parent names
        Arrays.fill(oldParents, root); //By default all nodes at depth two are children of root

        switch(treeDepth)
        {
            case 1:
                addRootLeafNode();               
            break;

            case 2:
                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 1);
                
                //ADD LEAF NODES TO TREE
                names = data.getDepthThree(); //Retrieve list of names specific to tree depth
                addLeafNodes(numChild, names, newParents, treeDepth+1); 
            break;

            case 3:
                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 1);
                
                //SETUP REQUIRED FOR MULTILEVELED TREES
                oldParents = newParents; //Make copy of parents to be used for next level of nodes
                numChild = genRandInt(2, 5); //Generate new number of children
                names = data.getDepthThree(); //Retrieve list of names specific to tree depth

                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 3);

                //ADD LEAF NODES TO TREE
                names = data.getDepthFour(); //Retrieve list of names specific to tree depth
                addLeafNodes(numChild, names, newParents, treeDepth+1);
            break;

            case 4:
                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 1);
                
                //SETUP REQUIRED FOR MULTILEVELED TREES
                oldParents = newParents; //Make copy of parents to be used for next level of nodes
                numChild = genRandInt(2, 5); //Generate new number of children
                names = data.getDepthThree(); //Retrieve list of names specific to tree depth

                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 3);

                //SETUP REQUIRED FOR MULTILEVELED TREES
                oldParents = newParents; //Make copy of parents to be used for next level of nodes
                numChild = genRandInt(2, 5); //Generate new number of children
                names = data.getDepthFour(); //Retrieve list of names specific to tree depth

                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 4);

                //ADD LEAF NODES TO TREE
                names = data.getDepthFive(); //Retrieve list of names specific to tree depth
                addLeafNodes(numChild, names, newParents, treeDepth+1);
            break;

            case 5:
                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 1);
                
                //SETUP REQUIRED FOR MULTILEVELED TREES
                oldParents = newParents; //Make copy of parents to be used for next level of nodes
                numChild = genRandInt(2, 5); //Generate new number of children
                names = data.getDepthThree(); //Retrieve list of names specific to tree depth

                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 3);

                //SETUP REQUIRED FOR MULTILEVELED TREES
                oldParents = newParents; //Make copy of parents to be used for next level of nodes
                numChild = genRandInt(2, 5); //Generate new number of children
                names = data.getDepthFour(); //Retrieve list of names specific to tree depth

                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 4);

                //SETUP REQUIRED FOR MULTILEVELED TREES
                oldParents = newParents; //Make copy of parents to be used for next level of nodes
                numChild = genRandInt(2, 5); //Generate new number of children
                names = data.getDepthFive(); //Retrieve list of names specific to tree depth

                //ADD SUBNODE TO TREE
                newParents = addSubNode(numChild, names, oldParents, 5);

                //ADD LEAF NODES TO TREE
                names = data.getDepthSix(); //Retrieve list of names specific to tree depth
                addLeafNodes(numChild, names, newParents, treeDepth+1);
            break;
        }
    }

    /**
     * This method is used in the event that only one node is generated,
     * the root node. In which case, the root node will only have a single leaf
     * node. 
     * NOTE: This assignment specifications do not clearly indicate how to handle
     *       such an instance. Therefore, i have made the assumption to handle it
     *       as described above.
     */
    private void addRootLeafNode()
    {
        Node newNode;
        PowerCategory usage;

        //Generate power usage data
        data.genPowerCategories();
        usage = data.getPowerUsage();

        //Create leaf node
        newNode = new PowerUsage("Perth", root, treeDepth, usage);

        //Add to tree
        cityNetwork.addNode(newNode); 
        cont.updateTotalPower(newNode.getNodeValues(), cityNetwork);
    }

    /**
     * Create and add sub-node to the City Network tree.
     * @param numChild Determines the number of nodes to create and add
     * @param names Pool of names to use for each node created
     * @param oldParents List of each parent node's name
     * @param depth Identifies the depth of the nodes within the tree
     * @return List of node names created, to be used for next set of child nodes
     */
    private String[] addSubNode(int numChild, List<String> names, String[] oldParents, int depth)
    {
        String name;
        Node newNode;
        String[] newParents;

        newParents = new String[numChild]; //Track parent names to be used for child nodes

        //Loop to add nodes to tree
        for(int i = 0; i < numChild; i++)
        {
            name = names.get(i);
            newParents[i] = name; //Add to list of parents

            //CREATE NON-ROOT NODE
            //Check that for loop doesn't exceed the bounds of oldParents array
            if(i < oldParents.length)
            {
                newNode = new SubCityNode(name, oldParents[i], depth);
            }
            else //If so, default to using last element in oldParents array
            {
                newNode = new SubCityNode(name, oldParents[oldParents.length-1], depth);
            }
                                
            //Add node to tree
            cityNetwork.addNode(newNode);
        }

        return newParents;
    }

    /**
     * Create and add a leaf node to the City Network tree
     * @param numChild Determines the number of leaf nodes to create and add
     * @param names Pool of names to use for each leaf node created
     * @param parents List of each parent node's name
     * @param depth Identifies the depth of the nodes within the tree
     */
    private void addLeafNodes(int numChild, List<String> names, String[] parents, int depth)
    {
        String name;
        Node leafNode;
        PowerCategory usage;

        for(int i = 0; i < numChild; i++)
        {
            //Get name
            name = names.get(i);

            //Generate power usage data
            data.genPowerCategories();
            usage = data.getPowerUsage();

            //Create leaf node
            leafNode = new PowerUsage(name, parents[i], depth, usage);

            //Add to tree
            cityNetwork.addNode(leafNode);
            cont.updateTotalPower(leafNode.getNodeValues(), cityNetwork);
        }
    }
}
