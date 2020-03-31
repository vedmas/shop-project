package ru.tokarev.shop.repository.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Roles implements Serializable {

    private static final long serialVersionUID = 7631527242474301333L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nameRole;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return nameRole.equals(roles.nameRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameRole);
    }
}
