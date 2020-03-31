package ru.tokarev.shop.service.statusPayService;

import ru.tokarev.shop.repository.entity.StatusPay;

import java.util.List;

public interface StatusPayService {

    List<StatusPay> findAll();

    StatusPay get(Integer id);

    void saveStatusPay(StatusPay statusPay);

    void deleteById(Integer id);

    void delete(StatusPay statusPay);

    StatusPay findOneByNameStatus(String nameStatus);
}
