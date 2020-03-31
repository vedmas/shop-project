package ru.tokarev.shop.flow.register;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class BasicUserInfo implements Serializable {

    @NotBlank
    private String email;

    @NotBlank
    private String numberPhone;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
