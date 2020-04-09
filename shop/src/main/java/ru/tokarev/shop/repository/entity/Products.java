package ru.tokarev.shop.repository.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Products implements Serializable {

    private static final long serialVersionUID = -7905811165075556076L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProduct;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producers producer;

    private BigDecimal price;
    private byte discount;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(
            name = "products_pictures",
    joinColumns = @JoinColumn(name = "products_id"),
    inverseJoinColumns = @JoinColumn(name = "pictures_id")
    )
    private List<Picture> pictures;

    @OneToMany(mappedBy = "products")
    private Set<OrdersProducts> orderProductsList;

    public Products(String nameProduct, Category category, Producers producer, BigDecimal price, byte discount) {
        this.nameProduct = nameProduct;
        this.category = category;
        this.producer = producer;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameProduct);
    }
}
