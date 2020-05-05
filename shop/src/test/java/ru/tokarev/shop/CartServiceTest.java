package ru.tokarev.shop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tokarev.shop.controller.repr.ProductRepr;
import ru.tokarev.shop.service.cart.CartService;
import ru.tokarev.shop.service.repr.ProductInfo;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {

    @Autowired
    CartService cartService;

    @Before
    public void cartFillingCart() {
        ProductRepr productRepr = new ProductRepr();
        productRepr.setPrice(new BigDecimal("10.00"));
        for (int i = 0; i < 5; i++) {
            cartService.addProductInfo(new ProductInfo(productRepr, 1L));
        }
    }

    @Test
    public void checkCountProductInCartTest() {
        Assert.assertEquals(5, cartService.findAll().get(0).getCount().intValue());
    }

    @Test
    public void checkPriceCartTest() {
        Assert.assertEquals(new BigDecimal("50.00"), cartService.findAll().get(0).getAllPrice());
    }
}
