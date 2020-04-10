package ru.tokarev.shop.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.tokarev.shop.service.repr.SystemUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 3992815266391699894L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstUserName;
    private String lastUserName;
    private String numberPhone;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles", //название таблицы
            joinColumns = @JoinColumn(name = "users_id"), //то, что связываем
            inverseJoinColumns = @JoinColumn(name = "roles_id") //то на что завязываем
    )
    @ToString.Exclude
    private Set<Roles> roles;

    public Users() {
        this.roles = new HashSet<>();
    }

    public Users(String firstUserName, String lastUserName, String numberPhone, String password, String email, Gender gender,
                 Set<Roles> roles) {
        this.firstUserName = firstUserName;
        this.lastUserName = lastUserName;
        this.numberPhone = numberPhone;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.roles = roles;
    }

    public Users(String firstUserName, String lastUserName, String numberPhone, String password, String email, Gender gender) {
        this.firstUserName = firstUserName;
        this.lastUserName = lastUserName;
        this.numberPhone = numberPhone;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.roles = new HashSet<>();
    }

    public Users(SystemUser systemUser) {
        this.id = systemUser.getId();
        this.firstUserName = systemUser.getFirstUserName();
        this.lastUserName = systemUser.getLastUserName();
        this.numberPhone = systemUser.getNumberPhone();
        this.password = systemUser.getPassword();
        this.email = systemUser.getEmail();
        this.gender = systemUser.getGender();
        this.roles = systemUser.getRoles();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id.equals(users.id) &&
                numberPhone.equals(users.numberPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberPhone);
    }
}
