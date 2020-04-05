package ru.tokarev.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.tokarev.shop.repository.PictureRepository;
import ru.tokarev.shop.repository.entity.Picture;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ImageController {

    private PictureRepository pictureRepository;

    @Autowired
    public ImageController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/picture/{pictureID}")
    public void downloadProductImage(@PathVariable("pictureID") Long pictureId, HttpServletResponse response) throws IOException {
        Optional<Picture> picture = pictureRepository.findById(pictureId);
        if(picture.isPresent()) {
            response.setContentType(picture.get().getContentType());
            response.getOutputStream().write(picture.get().getPictureData().getData());
        }
    }
}
