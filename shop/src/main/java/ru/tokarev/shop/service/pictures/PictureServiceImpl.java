package ru.tokarev.shop.service.pictures;

import ru.tokarev.shop.repository.entity.Picture;

import java.io.Serializable;
import java.util.List;

public class PictureServiceImpl implements PictureService, Serializable {

    private static final long serialVersionUID = -8459474349174642633L;

    @Override
    public List<Picture> findAll() {
        return null;
    }

    @Override
    public Picture get(Long id) {
        return null;
    }

    @Override
    public void addPicture(Picture picture) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Picture picture) {

    }

    @Override
    public Picture findOneByName(String name) {
        return null;
    }
}
