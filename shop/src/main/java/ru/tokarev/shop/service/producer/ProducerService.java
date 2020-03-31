package ru.tokarev.shop.service.producer;

import ru.tokarev.shop.controller.repr.ProducerRepr;
import ru.tokarev.shop.repository.entity.Producers;

import java.util.List;

public interface ProducerService {

    List<Producers> findAll();

    Producers get(Long id);

    void saveProducer(ProducerRepr producerRepr);

    void deleteById(Long id);

    void delete(Producers producer);

    Producers findOneByNameProducers(String producersName);
}
