package notification;

import java.util.ArrayList;
import java.util.List;

public class CommandeObserver {
    private final List<INotification> notifications = new ArrayList<>();

    public void addObserver(INotification o) {
        this.notifications.add(o);
    }

    public List<INotification> getNotifications() {
        return notifications;
    }

    public void notifier(String message) {
        for (INotification n : this.getNotifications()) {
            n.update(message);
        }
    }
}
