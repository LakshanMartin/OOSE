/**
 * Interface representing various Airlock states.
 * Implementations:
 *  - Idle
 *  - Pressurising
 *  - Depressurising
 */
public interface AirlockState 
{
    void pressurise(Airlock context);
    
    void depressurise(Airlock context);

    void openInnerDoor(Airlock context);

    void openOuterDoor(Airlock context);
}
