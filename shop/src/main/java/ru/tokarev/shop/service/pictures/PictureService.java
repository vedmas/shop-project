package ru.tokarev.shop.service.pictures;

import ru.tokarev.shop.repository.entity.Picture;
import ru.tokarev.shop.repository.entity.Roles;

import java.util.List;

public interface PictureService {

    List<Picture> findAll();

    Picture get(Long id);

    void addPicture(Picture picture);

    void deleteById(Long id);

    void delete(Picture picture);

    Picture findOneByName(String name);
}
