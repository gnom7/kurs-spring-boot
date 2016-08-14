package otg.k.kurs.event;

import org.springframework.context.ApplicationEvent;
import otg.k.kurs.domain.ForgotPasswordToken;
import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;

import java.util.Locale;

public class ForgotPasswordEvent extends ApplicationEvent {

    private final String applicationUrl;

    private final ForgotPasswordToken token;

    public ForgotPasswordEvent(User user, ForgotPasswordToken token, String applicationUrl) {
        super(user);
        this.token = token;
        this.applicationUrl = applicationUrl;
    }

    public ForgotPasswordToken getToken() {
        return token;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }
}
