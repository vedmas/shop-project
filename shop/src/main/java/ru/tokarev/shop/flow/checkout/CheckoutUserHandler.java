package ru.tokarev.shop.flow.checkout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tokarev.shop.controller.repr.OrderRepr;
import ru.tokarev.shop.repository.entity.*;
import ru.tokarev.shop.service.cart.CartService;
import ru.tokarev.shop.service.mailSender.MailSendService;
import ru.tokarev.shop.service.order.OrderService;
import ru.tokarev.shop.service.ordersProducts.OrdersProductsService;
import ru.tokarev.shop.service.repr.MailInfo;
import ru.tokarev.shop.service.repr.ProductInfo;
import ru.tokarev.shop.service.repr.SystemUser;
import ru.tokarev.shop.service.repr.UserInfo;
import ru.tokarev.shop.service.statusPayService.StatusPayService;
import ru.tokarev.shop.service.user.UserService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class CheckoutUserHandler {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutUserHandler.class);

    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";
    private static final int LENGTH_NUMBER_PHONE = 6;

    @Value("${path.email.page}")

    private String pathEmailPage;

    private UserService userService;

    private OrderService orderService;

    private StatusPayService statusPayService;

    private CartService cartService;

    private OrdersProductsService ordersProductsService;

    private MailSendService mailSendService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setStatusPayService(StatusPayService statusPayService) {
        this.statusPayService = statusPayService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setOrdersProductsService(OrdersProductsService ordersProductsService) {
        this.ordersProductsService = ordersProductsService;
    }

    @Autowired
    public void setMailSendService(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }

    @Autowired
    public CheckoutUserHandler(UserService userService) {
        this.userService = userService;
    }

    public CheckoutUserModel init() {
        return new CheckoutUserModel();
    }

    public void updateCheckoutUserInfo(CheckoutUserInfo checkoutUserInfo) {
        UserInfo userInfo = userService.authUserInfo();
        checkoutUserInfo.setLastName(userInfo.getLastUserName());
        checkoutUserInfo.setFirstName(userInfo.getFirstUserName());
        checkoutUserInfo.setNumberPhone(userInfo.getNumberPhone());
        checkoutUserInfo.setEmail(userInfo.getEmail());
    }

    public void addCheckoutUserInfo(CheckoutUserModel checkoutUserModel, CheckoutUserInfo checkoutUserInfo) {
        checkoutUserModel.setCheckoutUserInfo(checkoutUserInfo);
    }

    public void addCheckoutUserAddress(CheckoutUserModel checkoutUserModel, CheckoutUserAddress checkoutUserAddress) {
        checkoutUserModel.setCheckoutUserAddress(checkoutUserAddress);
    }

    public String validateCheckoutUserInfo(CheckoutUserInfo checkoutUserInfo, MessageContext error) {
        if (checkoutUserInfo.getNumberPhone().length() < LENGTH_NUMBER_PHONE) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("numberPhone")
                    .defaultText("Phone number at least 6 digits")
                    .build());
            return FAILURE;
        }
        return SUCCESS;
    }

    public String validateCheckoutUsrAddress(CheckoutUserAddress checkoutUserAddress, MessageContext error) {
        return SUCCESS;
    }

    @Transactional
    public String saveOrder(CheckoutUserModel checkoutUserModel, MessageContext error) {
        try {
            OrderRepr orderRepr = new OrderRepr();
            Set<OrdersProducts> ordersProductsSet = new HashSet<>();
            SystemUser systemUser = userService.findUserByNumberPhone(checkoutUserModel.getCheckoutUserInfo().getNumberPhone());
            if (systemUser != null) {
                Users users = new Users(systemUser);
                orderRepr.setUser(users);
            }
            orderRepr.setStatusPay(statusPayService.findOneByNameStatus("Payment received"));
            orderRepr.setAddress(checkoutUserModel.getCheckoutUserAddress().getAddress());
            orderRepr.setCity(checkoutUserModel.getCheckoutUserAddress().getCity());
            orderRepr.setCountry(checkoutUserModel.getCheckoutUserAddress().getCountry());
            orderRepr.setZipCode(checkoutUserModel.getCheckoutUserAddress().getZipCode());
            orderRepr.setOrderProductsList(ordersProductsSet);
            Orders orders = orderService.saveOrder(orderRepr);
            for (ProductInfo productInfo : cartService.findAll()) {
                ordersProductsService.saveOrderProduct(orders, productInfo);
            }
            Map<String, Object> model = new HashMap<>();
            model.put("orders", orders);
            Map<String, String> attachFile = new HashMap<>();
            attachFile.put("attach.bmp", "/templates/email-templates/attach/attach.bmp");
            MailInfo mailInfo = new MailInfo(
                    checkoutUserModel.getCheckoutUserInfo().getEmail(),
                    "Your order for Shop-Electro",
                    pathEmailPage + "e-msg-order",
                    attachFile,
                    model);
            mailSendService.sendMailHtml(mailInfo);
            cartService.deleteAll();
        } catch (Exception e) {
            logger.error("", e);
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Internal error. Can't complete save order.")
                    .build());
            return FAILURE;
        }
        return SUCCESS;
    }
}
