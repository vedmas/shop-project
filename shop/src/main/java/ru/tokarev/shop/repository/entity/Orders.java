package ru.tokarev.shop.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Orders implements Serializable {

    private static final long serialVersionUID = -4228853601167371657L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "status_payment_id")
    private StatusPay statusPay;

    private String address;

    private String city;

    private String country;

    @Column(name = "zip_code")
    private String zipCode;


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "orders_products",
//    joinColumns = @JoinColumn(name = "orders_id"),
//    inverseJoinColumns = @JoinColumn(name = "products_id"))

//    private Set<Products> productsList;

    @OneToMany(mappedBy = "orders")
    private Set<OrdersProducts> orderProductsList;

    public Orders(Long id, Users user, StatusPay statusPay, String address, String city, String country, String zipCode, Set<OrdersProducts> orderProductsList) {
        this.id = id;
        this.user = user;
        this.statusPay = statusPay;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.orderProductsList = orderProductsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", user=" + user.getNumberPhone() +
                ", statusPay=" + statusPay +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}


