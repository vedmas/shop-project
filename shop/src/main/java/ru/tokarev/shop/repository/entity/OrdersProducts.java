package ru.tokarev.shop.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class OrdersProducts implements Serializable {

    private static final long serialVersionUID = -8071762596040136947L;

    @EmbeddedId
    private OrdersProductsKey id;

    @ManyToOne
    @MapsId(value = "orders_id")
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne
    @MapsId("products_id")
    @JoinColumn(name = "products_id")
    private Products products;

    private Long quantity;

    @Override
    public String toString() {
        return "OrdersProducts{" +
                "id=" + id +
                ", orders=" + orders.getId() +
                ", products=" + products.getNameProduct() +
                ", quantity=" + quantity +
                '}';
    }
}
