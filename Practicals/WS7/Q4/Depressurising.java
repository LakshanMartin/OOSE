/**
 * Represents the Depressurising state of the Airlock.
 */
public class Depressurising implements AirlockState
{
    //CONSTRUCTOR
    public Depressurising(){}

    @Override
    public void pressurise(Airlock context) 
    {
        Door innerDoor = context.getInnerDoor();
        Door outerDoor = context.getOuterDoor();
        Pump pump = context.getPump();

        pump.stop(); //Assuming depressuring needs to be stopped before pressurising  
        pump.beginReturn(); //Action
        context.setState(new Pressurising()); //New state
    }

    @Override
    public void depressurise(Airlock context) 
    {
        System.out.println("Already depressurising!");
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
                context.setState(new Idle());
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
