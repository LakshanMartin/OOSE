package org.lucky.electricityNetwork.controller.FileIO;

import java.io.*;
import java.io.IOException;
import java.util.List;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.*;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.*;
import org.lucky.electricityNetwork.controller.CityNodeController;

public class ReadFile 
{
    //CLASS FIELDS
    private String defaultError;
    private String customError;
    private String exit;
    private CityNode cityNetwork;
    private CityNodeController cont;
    private String root;

    //CONSTRUCTOR
    public ReadFile()
    {
        defaultError = "\nERROR: Input file is in an invalid format!";
        customError = null;
        exit = "\nExiting to Terminal...\n";
        cityNetwork = null;
        cont = new CityNodeController();
        root = null;
    }

    //ACCESSOR
    /**
     * Method to used once all the data has been validated and read in.
     * @return Tree structure 
     */
    public CityNode getCityNetwork()
    {
        return cityNetwork;
    }

    //SUPPORTING METHODS 
    /**
     * Read data in from a csv file to be used to build the Tree structure
     * @param filename
     * @throws IOException
     * @throws InvalidFormatException Custom exception
     */
    public void readFile(String filename) throws IOException, InvalidFormatException
    {
        BufferedReader reader;
        File dir, file;
        String line;
        String[] parts;
        int treeDepth, len;
        Node newNode;
        PowerCategory power;
        boolean isRootLeafNode;

        dir = new File("../resources/"); //Specify file location
        file = new File(dir, filename);
        reader = new BufferedReader(new FileReader(file));
        
        line = reader.readLine(); //Read 1st line to identify root node

        parts = line.split(",");
        isRootLeafNode = false;
        
        try
        {
            createRootNode(parts);
        }
        catch(InvalidFormatException e)
        {
            reader.close();
            throw e;
        }        

        line = reader.readLine(); //Read following line

        //VERIFY AT LEAST 1 LINE FOLLOWS ROOT NODE
        if(line == null)
        {
            reader.close();
            customError = "\n- Root Node must at least have a leaf node with power consumption values.\n";
            throw new InvalidFormatException(defaultError + customError + exit);
        }

        while(line != null && !isRootLeafNode) //Read following lines
        {
            if(line.trim().length() > 0)
            {
                parts = line.split(",");
                len = parts.length;

                if(len == 2) //SUB-NODES
                {
                    try
                    {
                        //Validations
                        checkNodeFormat(parts[0], parts[1]);

                        if(!parts[1].equals(root))
                        {
                            checkParentExists(parts[1]);
                            checkParentNotLeaf(parts[1]);
                        }
                        
                        checkDuplicate(parts[0]);
                        
                        //Identify position in tree
                        treeDepth = checkDepth(parts[1]);

                        //Create new node
                        newNode = new SubCityNode(parts[0], parts[1], treeDepth);
                        
                        //Add node
                        cityNetwork.addNode(newNode);
                    }   
                    //Catch exceptions thrown from Validation methods
                    catch(InvalidFormatException e)
                    {
                        reader.close();
                        throw e; 
                    }
                }
                else if(len <= 10) //LEAF-NODES
                {
                    try
                    {
                        //Check if root-leaf-node (city, city, ...)
                        isRootLeafNode = checkRootLeafNode(parts[0], parts[1]);

                        if(isRootLeafNode)
                        {
                            //Validate and build power categories
                            power = buildPowerCategories(parts);

                            //Default value for node depth is 1
                            treeDepth = 1;

                            //Create new node
                            newNode = new PowerUsage(root, root, treeDepth, power);  
                            
                            //Add node and update total power consumption for network
                            cityNetwork.addNode(newNode);
                            cont.updateTotalPower(newNode.getNodeValues(), cityNetwork);
                        }
                        else
                        {
                            //Validations
                            checkNodeFormat(parts[0], parts[1]);

                            if(!parts[1].equals(root))
                            {
                                checkParentExists(parts[1]);
                                checkParentNotLeaf(parts[1]);
                            }
                            
                            checkDuplicate(parts[0]);

                            //Validate and build power categories
                            power = buildPowerCategories(parts);

                            //Identify position in tree
                            treeDepth = checkDepth(parts[1]);

                            //Create new node
                            newNode = new PowerUsage(parts[0], parts[1], treeDepth, power);

                            //Add node and update total power consumption for network
                            cityNetwork.addNode(newNode);
                            cont.updateTotalPower(newNode.getNodeValues(), cityNetwork);
                        }
                    }
                    //Catch exceptions thrown from Validation methods
                    catch(InvalidFormatException e)
                    {
                        reader.close();
                        throw e; 
                    }
                }
                else
                {
                    reader.close();
                    customError = "\n- Too many power categories.\n";
                    throw new InvalidFormatException(defaultError + customError + exit);
                }
            }
            else
            {
                reader.close();
                customError = "\n- Input file must not contain blank lines.\n";
                throw new InvalidFormatException(defaultError + customError + exit);
            }

            line = reader.readLine(); //Read next line

            //Check that no lines follow a root leaf node entry
            if(isRootLeafNode && line != null)
            {
                reader.close();
                customError = "\n- If Root Node is also a Leaf Node, then no other " +
                                "Child Nodes should exist.\n";
                throw new InvalidFormatException(defaultError + customError + exit);
            }
        }

        reader.close();
    }

    /**
     * Create Root Node after having been validated
     * @param parts
     * @throws InvalidFormatException
     */
    private void createRootNode(String[] parts) throws InvalidFormatException
    {
        if(parts.length == 1) //Must be single string
        {
            if(!parts[0].contains("=")) //Must not be a power category string
            {
                root = parts[0]; //Set root variable
                cityNetwork = new CityNode(root); //Create root node
            }
            else
            {
                customError = "\n- Root node can't be a power category.\n";
                throw new InvalidFormatException(defaultError + customError + exit);
            }
        }
        else
        {
            customError = "\n- First line from input file must be a single " +
                            "String representing the Root Node.\n";
            throw new InvalidFormatException(defaultError + customError + exit);
        }
    }

    /**
     * Validate the format of the node name and it's parent name
     * @param name
     * @param parent
     * @throws InvalidFormatException
     */
    private void checkNodeFormat(String name, String parent) throws InvalidFormatException
    {
        if(name.contains("=") || parent.contains("=")) //Expected format: [node],[parent]
        {
            customError = "\n- Input must be in format: [Node],[Parent].\n";
            throw new InvalidFormatException(defaultError + customError + exit);
        }
        else if(name.equals(parent))
        {
            customError = "\n- Node name and Parent name can't be the same.\n";
            throw new InvalidFormatException(defaultError + customError + exit);
        }
    }

    /**
     * Validate that the parent of a node exists in the list.
     * @param parent
     * @throws InvalidFormatException
     */
    private void checkParentExists(String parent) throws InvalidFormatException
    {
        List<Node> networkList;
        Node toCheck;
        boolean exists = false;

        networkList = cityNetwork.getNetworkList(); //Retrieve network list
        
        //Check if parents exists in network list
        for(int i = 0; i < networkList.size(); i++)
        {
            toCheck = networkList.get(i);

            if(toCheck.getName().equals(parent))
            {
                exists = true;
                break;
            }
        }

        //If not in network list, finally check if it matches Root Node
        if(!exists)
        {
            if(parent.equals(root))
            {
                exists = true;
            }
        }

        //Error state if it doesn't exist
        if(!exists)
        {
            customError = "\n- A Child Node without a Parent cannot exist in the Network.\n";
            throw new InvalidFormatException(defaultError + customError + exit);
        }
    }

    /**
     * Validate that a node's parent isn't listed as a leaf node
     * @param parent
     * @throws InvalidFormatException
     */
    private void checkParentNotLeaf(String parent) throws InvalidFormatException
    {
        Node parentNode;

        parentNode = cityNetwork.findNode(parent); //Retrieve parent node

        if(parentNode.isLeaf()) //Identify if it's a leaf node
        {
            customError = "\n- A Leaf Node cannot have any children.\n";
            throw new InvalidFormatException(defaultError + customError + exit);
        }        
    }

    /**
     * Validate that the node name is unique and doesn't already exist in network
     * @param name
     * @throws InvalidFormatException
     */
    private void checkDuplicate(String name) throws InvalidFormatException
    {
        if(cityNetwork.findNode(name) != null)
        {
            customError = "\n- Each Node input must be unique.\n";
            throw new InvalidFormatException(defaultError + customError + exit);
        }
    }

    /**
     * Wrapper method used for recursive algorithm to check depth of a node
     * @param parent
     * @return
     */
    private int checkDepth(String parent)
    {
        int depth;

        depth = 1; 

        if(!parent.equals(root))
        {
            depth += recursiveCheckDepth(parent, depth);
        }

        return depth;
    }
    
    /**
     * Recursive method to incrementally count the depth of a node by tracing 
     * it's parent node back to the root node.
     * @param parent
     * @param depth
     * @return
     */
    private int recursiveCheckDepth(String parent, int depth)
    {
        Node parentNode;

        if(!parent.equals(root)) //Base case
        {
            parentNode = cityNetwork.findNode(parent); //Retrieve parent node
            depth += recursiveCheckDepth(parentNode.getParent(), depth); //Recursive call
        }

        return depth;
    }

    /**
     * Identify if the node is actually a root leaf node.
     * NOTE: As this case was not specifically defined in the assignment notes, 
     *       I have made the assumption that root-leaf nodes will be represented
     *       in the following format:
     *          city, city, da=23.0,...
     * @param name
     * @param parent
     * @return true if is Root leaf node
     */
    private boolean checkRootLeafNode(String name, String parent)
    {
        boolean isRootLeafNode = false;

        if(name.equals(root))
        {
            if(parent.equals(root))
            {
                isRootLeafNode = true;
            }
        }

        return isRootLeafNode;
    }
   
    /**
     * Validate input data and use Decorator Design Pattern to build and return
     * Power Category object.
     * @param parts
     * @return
     * @throws InvalidFormatException
     */
    private PowerCategory buildPowerCategories(String[] parts) throws InvalidFormatException
    {
        String[] category;
        PowerCategory consumption;
        Double power;

        consumption = new ConcretePowerCategory(); //Create base object

        //Loop through any elements founds after [name],[parent]
        for(int i = 2; i < parts.length; i++)
        {
            //Isolate power category data from line
            category = parts[i].split("=");

            //Identify if "=" isn't used
            if(category.length < 2)
            {
                customError = "\n- Invalid Power Category input.\n";
                throw new InvalidFormatException(defaultError + customError + exit);
            }

            //Check input is in a real number format
            try
            {
                power = Double.parseDouble(category[1]);
            }
            catch(NumberFormatException e)
            {
                customError = "\n- Power consumption should be a real number.\n";
                throw new InvalidFormatException(defaultError + customError + exit);
            }
            
            //Add power consumption category decorations
            switch(category[0])
            {
                case "dm":
                    consumption = new WDayMorningDecoration(consumption, power);
                break;

                case "da":
                    consumption = new WDayANoonDecoration(consumption, power);
                break;

                case "de":
                    consumption = new WDayEveningDecoration(consumption, power);
                break;

                case "em":
                    consumption = new WEndMorningDecoration(consumption, power);
                break;

                case "ea":
                    consumption = new WEndANoonDecoration(consumption, power);
                break;

                case "ee":
                    consumption = new WEndEveningDecoration(consumption, power);
                break;

                case "h":
                    consumption = new HeatwaveDecoration(consumption, power);
                break;

                case "s":
                    consumption = new SpecEventDecoration(consumption, power);
                break;

                default:
                    customError = "\n- Invalid Power Category input.\n";
                    throw new InvalidFormatException(defaultError + customError + exit);
            }
        }

        //Return decorated Power Category object
        return consumption;        
    }
}
