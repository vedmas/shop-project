package ru.tokarev.shop.flow.checkout;

import lombok.Data;

import java.io.Serializable;

@Data
public class CheckoutUserModel implements Serializable {

    private CheckoutUserInfo checkoutUserInfo;

    private CheckoutUserAddress checkoutUserAddress;
}
