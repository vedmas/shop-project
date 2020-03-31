package ru.tokarev.shop.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrdersProductsKey implements Serializable {

    private static final long serialVersionUID = -5434859501768907532L;

    @Column(name = "orders_id")
    @NonNull
    private Long ordersId;

    @Column(name = "products_id")
    @NonNull
    private Long productsId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersProductsKey that = (OrdersProductsKey) o;
        return Objects.equals(ordersId, that.ordersId) &&
                Objects.equals(productsId, that.productsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordersId, productsId);
    }
}
