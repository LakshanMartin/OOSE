import java.util.List;

/**
 * Concrete Observer for displaying updated reminder list.
 */
public class ReminderViewer implements ReminderObserver
{
    private MainWindow window;

    public ReminderViewer(MainWindow window)
    {
        this.window = window;
    }

    @Override
    public void listUpdated(List<Reminder> reminders)
    {
        window.showReminders(reminders);
    }        
}
