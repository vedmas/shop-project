package ru.tokarev.shop.controller.repr;

import lombok.*;
import ru.tokarev.shop.repository.entity.Users;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class RoleRepr implements Serializable {

    private static final long serialVersionUID = 6766157956786310109L;

    private Long id;

    @NonNull
    private String nameRole;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Users> users;
}
