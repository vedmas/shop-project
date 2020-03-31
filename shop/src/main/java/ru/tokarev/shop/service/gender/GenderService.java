package ru.tokarev.shop.service.gender;

import ru.tokarev.shop.repository.entity.Gender;
import ru.tokarev.shop.repository.entity.Roles;

import java.util.List;

public interface GenderService {

    List<Gender> findAll();

    Gender get(int id);

    void addGenders(Gender article);

    void deleteById(int id);

    void delete(Gender gender);

    Gender findOneByNameGender(String nameGender);

    boolean isPresent(String nameGender);
}
