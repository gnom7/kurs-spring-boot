package otg.k.kurs.event;

import otg.k.kurs.domain.User;
import otg.k.kurs.domain.VerificationToken;
import otg.k.kurs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
//        VerificationToken verToken = userService.createVerificationToken(event.getUser());
        new Thread( () -> {confirmRegistration(event);} ).start();
    }

    @Async
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        try {
            mailSender.send(createMessage(user.getFirstname(), user.getEmail(), event.getToken(),
                    event.getApplicationUrl(), event.getLocale()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Async
    private MimeMessage createMessage(String name, String email, String token, String url, Locale locale) throws MessagingException {
        String subject = messages.getMessage("registration.confirmSubject", null, locale);
        String confirmationUrl = url
                + "/confirm?confirm_token=" + token;
        String message = messages.getMessage("registration.confirmMessage", new Object[] {name, confirmationUrl}, locale);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(message, true);
        return mimeMessage;
    }
}