package ru.tokarev.shop.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.tokarev.shop.aspect.TrackTime;
import ru.tokarev.shop.repository.entity.Gender;
import ru.tokarev.shop.repository.entity.Products;
import ru.tokarev.shop.service.product.ProductService;
import ru.tokarev.shop.service.repr.ProductInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("cartService")
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService, Serializable {

    private static final long serialVersionUID = 5489259139558716438L;
    private final List<ProductInfo> cartProducts;

    public CartServiceImpl() {
        cartProducts = new CopyOnWriteArrayList<>();
    }

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @TrackTime
    @Override
    public List<ProductInfo> findAll() {
        return Collections.unmodifiableList(cartProducts) ;
    }

    @Override
    public Gender getOneEntity(ProductInfo productInfo) {
        return null;
    }

    @TrackTime
    @Override
    public void addProductInfo(ProductInfo productInfo) {
        if(!existsInCollection(productInfo) || isEmptyCollection()) {
            cartProducts.add(productInfo);
        }
        else {
            for (ProductInfo cartProduct : cartProducts) {
                if (cartProduct.equals(productInfo)) {
                    cartProduct.setCount(cartProduct.getCount() + productInfo.getCount());
                    cartProduct.setAllPrice(cartProduct.getAllPrice().add(cartProduct.getProductRepr().getPrice()));
                    break;
                }
            }
        }
    }

    @Override
    public void delete(Long id) {
        for (ProductInfo cartProduct : cartProducts) {
            if(cartProduct.getProductRepr().equals(productService.get(id))) {
                if(cartProduct.getCount() > 1) {
                    cartProduct.setCount(cartProduct.getCount() - 1);
                }
                else cartProducts.remove(cartProduct);
            }
        }
    }

    @Override
    public BigDecimal subTotal() {
        return cartProducts.stream().map(ProductInfo::getAllPrice).
                reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Integer sizeCart() {
        return cartProducts.size();
    }

    @Override
    public void deleteAll() {
        cartProducts.clear();
    }

    private boolean existsInCollection(ProductInfo productInfo) {
        for (ProductInfo cartProduct : cartProducts) {
            if(cartProduct.equals(productInfo)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmptyCollection() {
        return cartProducts.size() == 0;
    }
}
