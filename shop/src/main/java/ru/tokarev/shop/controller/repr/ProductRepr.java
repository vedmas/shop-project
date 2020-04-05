package ru.tokarev.shop.controller.repr;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.tokarev.shop.repository.entity.Category;
import ru.tokarev.shop.repository.entity.Producers;
import ru.tokarev.shop.repository.entity.Products;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductRepr implements Serializable {

    private static final long serialVersionUID = -168563171109617142L;
    private Long id;

    private String nameProduct;

    private Category category;

    private Producers producer;

    private BigDecimal price;

    private byte discount;

    private List<PictureRepr> pictures;

    private MultipartFile[] newPictures;

    public ProductRepr(Products products) {
        this.id = products.getId();
        this.nameProduct = products.getNameProduct();
        this.category = products.getCategory();
        this.producer = products.getProducer();
        this.price = products.getPrice();
        this.discount = products.getDiscount();
        this.pictures = products.getPictures().stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ProductRepr{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", category=" + category +
                ", producer=" + producer +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
