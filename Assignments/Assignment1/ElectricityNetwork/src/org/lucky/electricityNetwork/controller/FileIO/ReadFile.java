package org.lucky.electricityNetwork.controller.FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.ConcretePowerCategory;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.HeatwaveDecoration;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.PowerCategory;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.SpecEventDecoration;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.WDayANoonDecoration;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.WDayEveningDecoration;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.WDayMorningDecoration;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.WEndANoonDecoration;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.WEndEveningDecoration;
import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.WEndMorningDecoration;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.CityNode;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.Node;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.PowerUsage;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.SubCityNode;
import org.lucky.electricityNetwork.controller.FileIO.InvalidFormatException;

public class ReadFile 
{
    private BufferedReader reader;
    private String defaultError;
    private String customError;
    private CityNode cityNetwork;
    private String root;

    //CONSTRUCTOR
    public ReadFile(String filename) throws FileNotFoundException
    {
        reader = new BufferedReader(new FileReader(filename));
        defaultError = "\nERROR: Input file is in an invalid format!";
        customError = null;
        cityNetwork = null;
        root = null;
    }

    public void readFile() throws IOException, InvalidFormatException
    {
        String line = reader.readLine();
        String[] parts;
        int treeDepth, len;
        Node newNode;
        PowerCategory power;
        boolean isRootLeafNode;

        parts = line.split(",");
        isRootLeafNode = false;
        
        try
        {
            createRootNode(parts);
        }
        catch(InvalidFormatException e)
        {
            throw e;
        }        

        line = reader.readLine(); //Read following line

        //VALIDATE AT LEAST 1 LINE FOLLOWS ROOT NODE
        if(line == null)
        {
            customError = "\n- Root Node must at least have power consumption values.\n";
            throw new InvalidFormatException(defaultError + customError);
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
                        checkParentExists(parts[1]);
                        checkDuplicate(parts[0]);
                        
                        //Identify position in tree
                        treeDepth = checkDepth(parts[1]);

                        //Create new node
                        newNode = new SubCityNode(parts[0], parts[1], treeDepth);
                        
                        //Add node
                        cityNetwork.addNode(newNode);
                    }   
                    catch(InvalidFormatException e)
                    {
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
                            //Build/Validate power categories
                            power = buildPowerCategories(line);

                            //Default value for node depth is 1
                            treeDepth = 1;

                            //Create new node
                            newNode = new PowerUsage(root, root, treeDepth, power);  
                            
                            //Add node
                            cityNetwork.addNode(newNode);
                        }

                        //Initial validations
                        //checkLeafNodeFormat(parts[0], parts[1]);
                        checkParentExists(parts[1]);
                        checkDuplicate(parts[0]);

                        //Build/Validate power categories


                    }
                    catch(InvalidFormatException e)
                    {
                        throw e;
                    }
                }
                else
                {
                    customError = "\n- Too many power categories.\n";
                    throw new InvalidFormatException(defaultError + customError);
                }
            }
            else
            {
                customError = "\n- Input file must not contain blank lines.\n";
                throw new InvalidFormatException(defaultError + customError);
            }

            line = reader.readLine(); //Read next line
        }
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
                root = parts[0]; //Set root node variable
                cityNetwork = new CityNode(root); //Create root node
            }
            else
            {
                customError = "\n- Root node can't be a power category.\n";
                throw new InvalidFormatException(defaultError + customError);
            }
        }
        else
        {
            customError = "\n- First line from input file must be a single " +
                            "String representing the Root Node.\n";
            throw new InvalidFormatException(defaultError + customError);
        }
    }

    private void checkNodeFormat(String name, String parent) throws InvalidFormatException
    {
        if(name.contains("=") || parent.contains("="))
        {
            customError = "\n- Input must be in format: [Node],[Parent].\n";
            throw new InvalidFormatException(defaultError + customError);
        }
        else if(name.equals(parent))
        {
            customError = "\n- Node name and Parent name can't be the same.\n";
            throw new InvalidFormatException(defaultError + customError);
        }
    }

    private void checkParentExists(String parent) throws InvalidFormatException
    {
        List<Node> networkList;
        Node toCheck;
        boolean exists = false;

        networkList = cityNetwork.getNetworkList();
        
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
            customError = "\n- Parent Node input doesn't exist in Network.\n";
            throw new InvalidFormatException(defaultError + customError);
        }
    }

    private void checkDuplicate(String name) throws InvalidFormatException
    {
        if(cityNetwork.findNode(name) != null)
        {
            customError = "\n- Each Node input must be unique.\n";
            throw new InvalidFormatException(defaultError + customError);
        }
    }

    private int checkDepth(String parent)
    {
        Node parentNode;
        int depth;

        depth = 1;

        if(!parent.equals(root))
        {
            parentNode = cityNetwork.findNode(parent);
            depth += checkDepth(parentNode.getParent());
        }

        return depth;
    }

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

    /*private boolean checkRootLeafNode(String[] parts) throws InvalidFormatException
    {
        boolean isRootLeafNode = true;
        customError = "\n- Power consumption category not in valid format";

        if(!parts[0].equals(root))
        {
            isRootLeafNode = false;
        }
        else
        {
            for(int i = 1; i < parts.length; i++)
            {
                if(!parts[i].contains("="))
                {
                    throw new InvalidFormatException(defaultError + customError);
                }    
            }
        }

        return isRootLeafNode;
    }*/

    

    private PowerCategory buildPowerCategories(String line) throws InvalidFormatException
    {
        String[] parts, category;
        PowerCategory consumption;
        Double power;

        parts = line.split(",");
        consumption = new ConcretePowerCategory();

        for(int i = 2; i < parts.length; i++)
        {
            //Isolate power category data from line
            category = parts[i].split("=");

            //Check input is in a real number format
            try
            {
                
                power = Double.parseDouble(category[1]);
            }
            catch(NumberFormatException e)
            {
                customError = "\n- Power consumption should be a real number.\n";
                throw new InvalidFormatException(defaultError + customError);
            }

            switch(category[0])
            {
                case "dm":
                    consumption = new WDayMorningDecoration(power);
                break;

                case "da":
                    consumption = new WDayANoonDecoration(power);
                break;

                case "de":
                    consumption = new WDayEveningDecoration(power);
                break;

                case "em":
                    consumption = new WEndMorningDecoration(power);
                break;

                case "ea":
                    consumption = new WEndANoonDecoration(power);
                break;

                case "ee":
                    consumption = new WEndEveningDecoration(power);
                break;

                case "h":
                    consumption = new HeatwaveDecoration(power);
                break;

                case "s":
                    consumption = new SpecEventDecoration(power);
                break;

                default:
                    customError = "\n- Invalid Power Category input.\n";
                    throw new InvalidFormatException(defaultError + customError);
            }
        }

        return consumption;        
    }
}
