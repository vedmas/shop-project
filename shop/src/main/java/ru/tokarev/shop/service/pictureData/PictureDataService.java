package ru.tokarev.shop.service.pictureData;

import ru.tokarev.shop.repository.entity.Picture;
import ru.tokarev.shop.repository.entity.PictureData;

import java.util.List;

public interface PictureDataService {
    List<PictureData> findAll();

    PictureData get(Long id);

    void savePicture(PictureData pictureData);

    void deleteById(Long id);

    void delete(PictureData pictureData);

}
