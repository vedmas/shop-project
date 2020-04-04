package ru.tokarev.shop.controller.repr;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tokarev.shop.repository.entity.OrdersProducts;
import ru.tokarev.shop.repository.entity.StatusPay;
import ru.tokarev.shop.repository.entity.Users;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class OrderRepr implements Serializable {

    private static final long serialVersionUID = -761768335608051934L;

    private Long id;

    private Users user;

    private StatusPay statusPay;

    private String address;

    private String city;

    private String country;

    private String zipCode;

    private Set<OrdersProducts> orderProductsList;

    public OrderRepr(Long id, Users user, StatusPay statusPay, String address, String city, String country, String zipCode, Set<OrdersProducts> orderProductsList) {
        this.id = id;
        this.user = user;
        this.statusPay = statusPay;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.orderProductsList = orderProductsList;
    }
}
