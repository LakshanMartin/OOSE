/**
 * Represents the Pressurising state of the Airlock.
 */
public class Pressurising implements AirlockState
{
    //CONSTRUCTOR
    public Pressurising(){}

    @Override
    public void pressurise(Airlock context) 
    {
        System.out.println("Already pressurising");
    }

    @Override
    public void depressurise(Airlock context) 
    {
        Door innerDoor = context.getInnerDoor();
        Door outerDoor = context.getOuterDoor();
        Pump pump = context.getPump();

        pump.stop(); //Action
        pump.beginExtraction(); //Action
        context.setState(new Depressurising()); //New state
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
                pump.stop();
                innerDoor.open();
                context.setState(new Idle());;
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
                pump.stop();
                outerDoor.open();
                context.setState(new Idle());
            }
            else
            {
                System.out.println("Pressure too high");
            }
        }
    }        
}
