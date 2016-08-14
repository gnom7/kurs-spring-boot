package otg.k.kurs.event;

import otg.k.kurs.domain.ForgotPasswordToken;
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
public class ForgotPasswordListener implements ApplicationListener<ForgotPasswordEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void onApplicationEvent(ForgotPasswordEvent event) {
        new Thread( () -> {confirmRegistration(event);} ).start();
    }

    @Async
    private void confirmRegistration(ForgotPasswordEvent event) {
        try {
            mailSender.send(createMessage(event.getToken(), event.getApplicationUrl()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Async
    private MimeMessage createMessage(ForgotPasswordToken token, String url) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(token.getUser().getEmail());
        String msg = "<a href=\"" + url + "/forgotPassword?token=" + token.getToken() + "\">Change password</a>";
        helper.setSubject("Forgot password");
        helper.setText(msg, true);
        return mimeMessage;
    }
}