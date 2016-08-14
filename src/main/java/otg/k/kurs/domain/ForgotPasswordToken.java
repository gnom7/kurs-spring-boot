package otg.k.kurs.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "forgot_password_tokens")
public class ForgotPasswordToken {

    @Id
    private String token;

    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public ForgotPasswordToken() {
    }

    public ForgotPasswordToken(String token, User user, Date expirationDate) {
        this.token = token;
        this.user = user;
        this.expirationDate = expirationDate;
    }
}
