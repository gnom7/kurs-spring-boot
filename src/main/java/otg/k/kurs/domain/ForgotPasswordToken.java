package otg.k.kurs.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "forgot_password_tokens")
public class ForgotPasswordToken {

    @Id
    private String token;

    private String email;

    private Date expirationDate;

    public ForgotPasswordToken() {
    }

    public ForgotPasswordToken(String token, String email, Date expirationDate) {
        this.token = token;
        this.email = email;
        this.expirationDate = expirationDate;
    }
}
