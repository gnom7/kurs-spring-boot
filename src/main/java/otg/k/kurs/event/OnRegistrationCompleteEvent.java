package otg.k.kurs.event;

import org.springframework.context.ApplicationEvent;
import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String applicationUrl;

    private final Locale locale;

    private final User user;

    private final String token;

    public OnRegistrationCompleteEvent(User user, String token, String applicationUrl, Locale locale) {
        super(user);
        this.token = token;
        this.applicationUrl = applicationUrl;
        this.locale = locale;
        this.user = user;
    }

    public String getToken() {
        return token;
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
