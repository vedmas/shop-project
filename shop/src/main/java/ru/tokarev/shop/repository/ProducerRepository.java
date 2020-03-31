package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.Producers;

public interface ProducerRepository extends JpaRepository<Producers, Long> {
    Producers findOneByNameProducer(String nameProducer);
}
