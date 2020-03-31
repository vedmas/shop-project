package ru.tokarev.shop.service.repr;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.tokarev.shop.controller.repr.ProductRepr;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 267688168102987471L;
    private ProductRepr productRepr;

    @EqualsAndHashCode.Exclude
    private Long count;

    @EqualsAndHashCode.Exclude
    private BigDecimal allPrice;

    public ProductInfo(ProductRepr productRepr, Long count) {
        this.productRepr = productRepr;
        this.count = count;
        this.allPrice = productRepr.getPrice().multiply(new BigDecimal(count));
    }
}
