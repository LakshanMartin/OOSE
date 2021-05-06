/**
 * Represents the Idle state of the Airlock.
 */
public class Idle implements AirlockState
{
    //CONSTRUCTOR
    public Idle(){}

    @Override
    public void pressurise(Airlock context)
    {
        Door innerDoor = context.getInnerDoor();
        Door outerDoor = context.getOuterDoor();
        Pump pump = context.getPump();
        double pressure = context.getPressure();

        if(!innerDoor.isOpen() && !outerDoor.isOpen()) //Guard condition
        {
            if(pressure < 110.0) //Guard condition
            {
                pump.beginReturn(); //Action
                context.setState(new Pressurising()); //New state
            }
            else
            {
                System.out.println("Already at maximum pressure");
            }
        }
        else
        {
            System.out.println("Close a door");
        }
    }

    @Override
    public void depressurise(Airlock context) 
    {
        Door innerDoor = context.getInnerDoor();
        Door outerDoor = context.getOuterDoor();
        Pump pump = context.getPump();
        double pressure = context.getPressure();

        if(!innerDoor.isOpen() && !outerDoor.isOpen()) //Guard condition
        {
            if(pressure > 0.0) //Guard condition
            {
                pump.beginExtraction(); //Action
                context.setState(new Depressurising()); //New state
            }
            else
            {
                System.out.println("Already at minimum pressure");
            }
        }
        else
        {
            System.out.println("Close a door");
        }
    }

    @Override
    public void openInnerDoor(Airlock context) 
    {
        Door innerDoor = context.getInnerDoor();
        Door outerDoor = context.getOuterDoor();
        Pump pump = context.getPump();
        double pressure = context.getPressure();

        if(!innerDoor.isOpen() && !outerDoor.isOpen())
        {
            if(pressure > 90)
            {
                innerDoor.open();
            }
            else
            {
                System.out.println("Pressure too low");
            }
        }
    }

    @Override
    public void openOuterDoor(Airlock context) 
    {
        Door innerDoor = context.getInnerDoor();
        Door outerDoor = context.getOuterDoor();
        Pump pump = context.getPump();
        double pressure = context.getPressure();

        if(!innerDoor.isOpen() && !outerDoor.isOpen())
        {
            if(pressure < 5)
            {
                outerDoor.open();
            }
            else
            {
                System.out.println("Pressure too high");
            }
        }
    }
}
