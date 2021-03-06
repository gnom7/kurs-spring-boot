package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.validators.PasswordMatches;
import otg.k.kurs.validators.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class AccountDto {

    @NotNull
    @Size(min = 2, max = 60)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 60)
    private String lastname;

    @ValidEmail
    @NotNull
    @Size(min = 6, max = 60)
    private String email;

    @NotNull
    @Size(min = 8, max = 60)
    private String password;

    private String matchingPassword;

    @NotNull
    @Size(min = 5, max = 60)
    private String username;

}
