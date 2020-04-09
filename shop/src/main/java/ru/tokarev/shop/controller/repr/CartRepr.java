package ru.tokarev.shop.controller.repr;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartRepr implements Serializable {

    private static final long serialVersionUID = -3133371391122653090L;

    private Long productId;

}
