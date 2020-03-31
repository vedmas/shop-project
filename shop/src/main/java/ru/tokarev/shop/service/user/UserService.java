package ru.tokarev.shop.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.tokarev.shop.repository.entity.Users;
import ru.tokarev.shop.service.repr.SystemUser;
import ru.tokarev.shop.service.repr.UserInfo;

import java.util.List;

public interface UserService extends UserDetailsService {

    SystemUser findById(Long id);

    SystemUser findByNumberPhone(String numberPhone);

    SystemUser findUserByNumberPhone(String numberPhone);

    boolean existsUserByEmail(String email);

    boolean save(SystemUser systemUser);

    boolean existsUserByNumberPhone(String numberPhone);

    void delete(Long id);

    UserInfo authUserInfo();

    List<SystemUser> findAll();


}
