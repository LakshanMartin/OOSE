import java.util.List;

/**
 * Concrete Observer for writing updated reminder list to file.
 */
public class ReminderWriter implements ReminderObserver
{
    private MainWindow window;

    public ReminderWriter(MainWindow window)
    {
        this.window = window;
    }

    @Override
    public void listUpdated(List<Reminder> reminders)
    {
        window.writeReminders(reminders);       
    }    
}
