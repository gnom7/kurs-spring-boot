package otg.k.kurs.event;

import org.springframework.context.ApplicationEvent;
import otg.k.kurs.domain.User;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String applicationUrl;

    private final Locale locale;

    private final User user;

    public OnRegistrationCompleteEvent(User user, String applicationUrl, Locale locale) {
        super(user);
        this.applicationUrl = applicationUrl;
        this.locale = locale;
        this.user = user;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }
}
