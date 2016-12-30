package controllers.notifications;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class NotificationCenter {
    private Vector<EventSubcriber>  subcribers;

    public final static NotificationCenter instance = new NotificationCenter();

    private NotificationCenter() {
        subcribers = new Vector<>();
    }

    public void register(EventSubcriber subcriber) {
        this.subcribers.add(subcriber);
    }

    public void OnEvent(EventType eventType, Object params) {
        Iterator <EventSubcriber> iterator = this.subcribers.iterator();
        while (iterator.hasNext()) {
            EventSubcriber e = iterator.next();
            if(!e.onEvent(eventType, params)) {
                iterator.remove();
            }
        }
    }
}
