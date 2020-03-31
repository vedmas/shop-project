package ru.tokarev.shop.service.role;

import ru.tokarev.shop.controller.repr.RoleRepr;
import ru.tokarev.shop.repository.entity.Roles;

import java.util.List;

public interface RoleService {

    List<Roles> findAll();

    Roles get(Long id);

    void saveRoles(RoleRepr roleRepr);

    void deleteById(Long id);

    void delete(Roles roles);

    Roles findOneByNameRole(String nameRole);
}
