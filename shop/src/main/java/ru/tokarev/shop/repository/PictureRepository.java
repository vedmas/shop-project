package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.Category;
import ru.tokarev.shop.repository.entity.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
