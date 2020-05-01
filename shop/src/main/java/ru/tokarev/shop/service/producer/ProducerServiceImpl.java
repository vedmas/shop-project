package ru.tokarev.shop.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.aspect.TrackTime;
import ru.tokarev.shop.controller.repr.ProducerRepr;
import ru.tokarev.shop.repository.ProducerRepository;
import ru.tokarev.shop.repository.entity.Producers;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService, Serializable {

    private static final long serialVersionUID = -4510798267559404865L;

    private ProducerRepository producerRepository;

    @Autowired
    public void setProducerRepository(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public List<Producers> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Producers get(Long id) {
        return producerRepository.findById(id).orElse(new Producers());
    }

    @Override
    @Transactional
    @TrackTime
    public void saveProducer(ProducerRepr producerRepr) {
        Producers producer = new Producers();
        producer.setId(producerRepr.getId());
        producer.setNameProducer(producerRepr.getNameProducer());
        producer.setRegion(producerRepr.getRegion());
        producer.setCountry(producerRepr.getCountry());
        producerRepository.save(producer);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        producerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Producers producer) {
        producerRepository.delete(producer);
    }

    @Override
    public Producers findOneByNameProducers(String producersName) {
        return producerRepository.findOneByNameProducer(producersName);
    }
}
