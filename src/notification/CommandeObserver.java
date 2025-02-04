package notification;

import java.util.ArrayList;
import java.util.List;

public class CommandeObserver {
    private final List<INotification> notifications = new ArrayList<>();

    public void addObserver(INotification o) {
        this.notifications.add(o);
    }

    public void notify(String message) {
        for (INotification n : this.notifications) {
            n.update(message);
        }
    }
}
