package ru.tokarev.shop.controller.repr;



import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tokarev.shop.controller.validation.NumberPhoneNotRegistered;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class SignupForm implements Serializable {

    private static final long serialVersionUID = -469320159103718712L;
    
    @NotBlank
    @NumberPhoneNotRegistered
    private String numberPhone;
}
