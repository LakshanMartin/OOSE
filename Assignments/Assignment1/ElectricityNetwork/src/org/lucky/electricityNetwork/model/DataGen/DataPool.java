package org.lucky.electricityNetwork.model.DataGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.*;

public class DataPool 
{
    private List<String> depthTwo;
    private List<String> depthThree;
    private List<String> depthFour;
    private List<String> depthFive;
    private List<String> depthSix;
    private PowerCategory consumption;

    public DataPool()
    {
        genDepthTwoPool();
        genDepthThreePool();
        genDepthFourPool();
        genDepthFivePool();
        genDepthSixPool();
    }

    //MUTATORS
    private void genDepthTwoPool()
    {
        depthTwo =  new ArrayList<>();

        depthTwo.add("North");
        depthTwo.add("South");
        depthTwo.add("East");
        depthTwo.add("West");
        depthTwo.add("Central");
    }

    private void genDepthThreePool()
    {
        depthThree = new ArrayList<>();

        depthThree.add("Shire1");
        depthThree.add("Shire2");
        depthThree.add("Shire3");
        depthThree.add("Shire4");
        depthThree.add("Shire5");
    }

    private void genDepthFourPool()
    {
        depthFour = new ArrayList<>();

        depthFour.add("Suburb1");
        depthFour.add("Suburb2");
        depthFour.add("Suburb3");
        depthFour.add("Suburb4");
        depthFour.add("Suburb5");
    }

    private void genDepthFivePool()
    {
        depthFive = new ArrayList<>();

        depthFive.add("Street1");
        depthFive.add("Street2");
        depthFive.add("Street3");
        depthFive.add("Street4");
        depthFive.add("Street5");
    }

    private void genDepthSixPool()
    {
        depthSix = new ArrayList<>();

        depthSix.add("Building1");
        depthSix.add("Building2");
        depthSix.add("Building3");
        depthSix.add("Building4");
        depthSix.add("Building5");
    }

    public void genPowerCategories()
    {
        double usage;

        consumption = new ConcretePowerCategory();
        usage = randUsage();
        consumption = new WDayMorningDecoration(consumption, usage);
        usage = randUsage();       
        consumption = new WDayANoonDecoration(consumption, usage);
        usage = randUsage();
        consumption = new WDayEveningDecoration(consumption, usage);
        usage = randUsage();
        consumption = new WEndMorningDecoration(consumption, usage);
        usage = randUsage();
        consumption = new WEndANoonDecoration(consumption, usage);
        usage = randUsage();
        consumption = new WEndEveningDecoration(consumption,usage);
        usage = randUsage();
        consumption = new HeatwaveDecoration(consumption, usage);
        usage = randUsage();
        consumption = new SpecEventDecoration(consumption, usage);
    }

    //ACCESSORS
    public List<String> getDepthTwo()
    {
        return depthTwo;
    }

    public List<String> getDepthThree()
    {
        return depthThree;
    }

    public List<String> getDepthFour()
    {
        return depthFour;
    }

    public List<String> getDepthFive()
    {
        return depthFive;
    }

    public List<String> getDepthSix()
    {
        return depthSix;
    }

    public PowerCategory getPowerUsage()
    {
        return consumption;
    }

    private double randUsage()
    {
        Random rand = new Random();
        int max;
        double result, decimal;

        max = 1000;
        result = rand.nextInt(max + 1); //Random whole number [0-1000]
        decimal = rand.nextInt(99 + 1) / 100.0; //Random decimal number [0.00-0.99]
        result += decimal; //Add to create random real number

        return result;
    }
}
