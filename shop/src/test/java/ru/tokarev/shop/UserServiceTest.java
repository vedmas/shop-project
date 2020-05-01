package ru.tokarev.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tokarev.shop.repository.UserRepository;
import ru.tokarev.shop.repository.entity.Users;
import ru.tokarev.shop.service.repr.SystemUser;
import ru.tokarev.shop.service.user.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findByNumberPhoneTest() {
        Users usersFromDB = new Users();
        usersFromDB.setNumberPhone("+78235624521");
        usersFromDB.setFirstUserName("Leo");
        Mockito.doReturn(usersFromDB)
                .when(userRepository).findOneByNumberPhone("+78235624521");
        SystemUser systemUser = userService.findByNumberPhone("+78235624521");
        Assert.assertNotNull(systemUser);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByNumberPhone("+78235624521");
    }
}
