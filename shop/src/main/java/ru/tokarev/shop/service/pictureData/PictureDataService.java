package ru.tokarev.shop.service.pictureData;

import ru.tokarev.shop.repository.entity.Picture;
import ru.tokarev.shop.repository.entity.PictureData;

import java.util.List;

public interface PictureDataService {

    void savePicture(PictureData pictureData);
}
