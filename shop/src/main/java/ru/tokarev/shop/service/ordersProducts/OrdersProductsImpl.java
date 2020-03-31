package ru.tokarev.shop.service.ordersProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.repository.OrdersProductsRepository;
import ru.tokarev.shop.repository.entity.Orders;
import ru.tokarev.shop.repository.entity.OrdersProducts;
import ru.tokarev.shop.repository.entity.OrdersProductsKey;
import ru.tokarev.shop.repository.entity.Products;
import ru.tokarev.shop.service.product.ProductService;
import ru.tokarev.shop.service.repr.ProductInfo;

import java.io.Serializable;
import java.util.List;

@Service
public class OrdersProductsImpl implements OrdersProductsService, Serializable {

    private static final long serialVersionUID = 5979223291972868248L;

    private OrdersProductsRepository ordersProductsRepository;
    private ProductService productService;

    @Autowired
    public OrdersProductsImpl(OrdersProductsRepository ordersProductsRepository, ProductService productService) {
        this.ordersProductsRepository = ordersProductsRepository;
        this.productService = productService;
    }

    @Override
    public List<OrdersProducts> findAll() {
        return ordersProductsRepository.findAll();
    }

    @Override
    public OrdersProducts get(OrdersProductsKey id) {
        return null;
    }

    @Override
    public OrdersProducts saveOrderProduct(Orders orders, ProductInfo productInfo) {
        OrdersProducts ordersProducts = new OrdersProducts();
        OrdersProductsKey ordersProductsKey = new OrdersProductsKey(orders.getId(), productInfo.getProductRepr().getId());
        ordersProducts.setOrders(orders);
        ordersProducts.setProducts(productService.findOneById(productInfo.getProductRepr().getId()));
        ordersProducts.setQuantity((productInfo.getCount()));
        ordersProducts.setId(ordersProductsKey);
        return ordersProductsRepository.save(ordersProducts);
    }

    @Override
    public void deleteById(OrdersProductsKey id) {

    }

    @Override
    public void delete(OrdersProducts ordersProducts) {

    }
}
