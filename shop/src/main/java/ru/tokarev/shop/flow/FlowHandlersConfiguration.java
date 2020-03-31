package ru.tokarev.shop.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tokarev.shop.flow.checkout.CheckoutUserHandler;
import ru.tokarev.shop.flow.register.UserRegisterHandler;
import ru.tokarev.shop.service.user.UserService;

@Configuration
public class FlowHandlersConfiguration {

    @Bean
    public UserRegisterHandler userRegisterHandler(UserService userService) {
        return new UserRegisterHandler(userService);
    }

//    @Bean
//    CheckoutUserHandler checkoutUserHandler(UserService userService) {
//        return new CheckoutUserHandler(userService);
//    }
}
