package ru.tokarev.shop.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.controller.repr.OrderRepr;
import ru.tokarev.shop.repository.OrderRepository;
import ru.tokarev.shop.repository.entity.Orders;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService, Serializable {

    private static final long serialVersionUID = -8091847961202201710L;

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orders get(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Orders saveOrder(OrderRepr orderRepr) {
        Orders orders = new Orders(orderRepr.getId(),
                orderRepr.getUser(),
                orderRepr.getStatusPay(),
                orderRepr.getAddress(),
                orderRepr.getCity(),
                orderRepr.getCountry(),
                orderRepr.getZipCode(),
                orderRepr.getOrderProductsList());
        return orderRepository.save(orders);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void delete(Orders order) {
        orderRepository.delete(order);
    }
}
