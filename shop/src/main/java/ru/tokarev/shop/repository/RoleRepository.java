package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findOneByNameRole(String nameRole);
}
