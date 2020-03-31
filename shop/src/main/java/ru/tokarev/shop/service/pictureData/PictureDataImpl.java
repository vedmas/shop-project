package ru.tokarev.shop.service.pictureData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.repository.PictureDataRepository;
import ru.tokarev.shop.repository.entity.PictureData;

import java.io.Serializable;
import java.util.List;

@Service
public class PictureDataImpl implements PictureDataService, Serializable {

    private static final long serialVersionUID = -1644041855617035847L;
    PictureDataRepository pictureDataRepository;

    @Autowired
    public void setPictureDataRepository(PictureDataRepository pictureDataRepository) {
        this.pictureDataRepository = pictureDataRepository;
    }

    @Override
    public List<PictureData> findAll() {
        return null;
    }

    @Override
    public PictureData get(Long id) {
        return null;
    }

    @Override
    public void savePicture(PictureData pictureData) {
        pictureDataRepository.save(pictureData);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(PictureData pictureData) {

    }
}
