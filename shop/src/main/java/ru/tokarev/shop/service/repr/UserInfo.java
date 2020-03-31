package ru.tokarev.shop.service.repr;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tokarev.shop.repository.entity.Gender;
import ru.tokarev.shop.repository.entity.Roles;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserInfo {

    private Long id;
    private String firstUserName;
    private String lastUserName;
    private String numberPhone;
    private String email;
    private Gender gender;
    private Set<Roles> roles;

    public UserInfo(Long id, String firstUserName, String lastUserName, String numberPhone, String email, Gender gender, Set<Roles> roles) {
        this.id = id;
        this.firstUserName = firstUserName;
        this.lastUserName = lastUserName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.gender = gender;
        this.roles = roles;
    }
}
