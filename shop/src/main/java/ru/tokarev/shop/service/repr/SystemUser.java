package ru.tokarev.shop.service.repr;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tokarev.shop.repository.entity.Gender;
import ru.tokarev.shop.repository.entity.Roles;
import ru.tokarev.shop.repository.entity.Users;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class SystemUser implements Serializable {

    private static final long serialVersionUID = -1662124004042085296L;

    private Long id;

    private String firstUserName;
    private String lastUserName;
    private String numberPhone;
    private String password;
    private String email;
    private Gender gender;
    private Set<Roles> roles;

    public SystemUser(String firstUserName, String lastUserName, String numberPhone, String password, String email, Gender gender, Set<Roles> roles) {
        this.firstUserName = firstUserName;
        this.lastUserName = lastUserName;
        this.numberPhone = numberPhone;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.roles = roles;
    }

    public SystemUser(String firstUserName, String lastUserName, String numberPhone, String password, String email) {
        this.firstUserName = firstUserName;
        this.lastUserName = lastUserName;
        this.numberPhone = numberPhone;
        this.password = password;
        this.email = email;
    }

    public SystemUser(Users user) {
        this.id = user.getId();
        this.firstUserName = user.getFirstUserName();
        this.lastUserName = user.getLastUserName();
        this.numberPhone = user.getNumberPhone();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.roles = user.getRoles();
    }
}
