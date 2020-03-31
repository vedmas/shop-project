package ru.tokarev.shop.flow.register;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class PersonalUserInfo implements Serializable {

    private static final long serialVersionUID = 4601400021668800962L;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String gender;
}
