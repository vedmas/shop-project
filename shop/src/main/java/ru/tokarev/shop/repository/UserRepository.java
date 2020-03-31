package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findOneByNumberPhone(String numberPhone);

    boolean existsUserByEmail(String email);
    boolean existsUserByNumberPhone(String numberPhone);
}
