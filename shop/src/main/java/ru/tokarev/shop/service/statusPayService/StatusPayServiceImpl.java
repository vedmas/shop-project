package ru.tokarev.shop.service.statusPayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.repository.StatusPayRepository;
import ru.tokarev.shop.repository.entity.StatusPay;

import java.io.Serializable;
import java.util.List;

@Service("statusPayService")
public class StatusPayServiceImpl implements StatusPayService, Serializable {

    private static final long serialVersionUID = -4102890212102264340L;

    private StatusPayRepository statusPayRepository;

    @Autowired
    public void setStatusPayRepository(StatusPayRepository statusPayRepository) {
        this.statusPayRepository = statusPayRepository;
    }

    @Override
    public List<StatusPay> findAll() {
        return statusPayRepository.findAll();
    }

    @Override
    public StatusPay get(Integer id) {
        return statusPayRepository.findById(id).get();
    }

    @Override
    public void saveStatusPay(StatusPay statusPay) {
    }

    @Override
    public void deleteById(Integer id) {
        statusPayRepository.deleteById(id);
    }

    @Override
    public void delete(StatusPay statusPay) {

    }

    @Override
    public StatusPay findOneByNameStatus(String nameStatus) {
        return statusPayRepository.findOneByNameStatus(nameStatus);
    }
}
