package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.PictureData;

public interface PictureDataRepository extends JpaRepository<PictureData, Long> {
}
