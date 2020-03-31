package ru.tokarev.shop.controller.repr;

import lombok.Data;
import ru.tokarev.shop.repository.entity.Picture;

import java.io.Serializable;

@Data
public class PictureRepr implements Serializable {

    private static final long serialVersionUID = -9018201549774873537L;
    private Long id;

    private String name;

    private String contentType;

    public PictureRepr(Picture picture) {
        this.id = picture.getId();
        this.name = picture.getName();
        this.contentType = picture.getContentType();
    }
}
