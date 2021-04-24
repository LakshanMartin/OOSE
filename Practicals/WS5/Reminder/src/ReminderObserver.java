import java.util.List;

public interface ReminderObserver 
{
    public void listUpdated(List<Reminder> reminders);
}
