package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
    Gender findOneByNameGender(String nameGender);
}
