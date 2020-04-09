package ru.tokarev.shop.service.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.repository.GenderRepository;
import ru.tokarev.shop.repository.RoleRepository;
import ru.tokarev.shop.repository.UserRepository;
import ru.tokarev.shop.repository.entity.Roles;
import ru.tokarev.shop.repository.entity.Users;
import ru.tokarev.shop.service.repr.SystemUser;
import ru.tokarev.shop.service.repr.UserInfo;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service("userServices")
@Slf4j
public class UserServiceJpaImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private GenderRepository genderRepository;

    @Autowired
    public void setGenderRepository(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Autowired
    public UserServiceJpaImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public SystemUser findById(Long id) {
        return new SystemUser(userRepository.findById(id).get());
    }

    @Override
    @Transactional
    public SystemUser findByNumberPhone(String numberPhone) {
        Users user = userRepository.findOneByNumberPhone(numberPhone);
        return new SystemUser(user.getFirstUserName(), user.getLastUserName(), user.getNumberPhone(), user.getPassword(),
                user.getEmail(), user.getGender(), user.getRoles());
    }

    @Override
    public SystemUser findUserByNumberPhone(String numberPhone) {
        if(!existsUserByNumberPhone(numberPhone)) {
              return null;
        }
        Users user = userRepository.findOneByNumberPhone(numberPhone);
        return new SystemUser(user);
    }

    @Override
    public UserInfo authUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if((userRepository.existsUserByNumberPhone(authentication.getName()))) {
            Users user = userRepository.findOneByNumberPhone(authentication.getName());
            return new UserInfo(user.getId(), user.getFirstUserName(), user.getLastUserName(), user.getNumberPhone(),
                    user.getEmail(), user.getGender(), user.getRoles());
        }
        return new UserInfo();
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public boolean existsUserByNumberPhone(String numberPhone) {
        return userRepository.existsUserByNumberPhone(numberPhone);
    }

    @Override
    @Transactional
    public boolean save(SystemUser systemUser) {
        Users user = systemUser.getId() != null ? userRepository.findById(systemUser.getId()).orElse(new Users()) : new Users();
        user.setNumberPhone(systemUser.getNumberPhone());
        if (systemUser.getId() == null || (systemUser.getPassword() != null && !systemUser.getPassword().trim().isEmpty())) {
            user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        }
        user.setFirstUserName(systemUser.getFirstUserName());
        user.setLastUserName(systemUser.getLastUserName());
        user.setEmail(systemUser.getEmail());
        if(systemUser.getGender() == null) {
            user.setGender(genderRepository.findOneByNameGender("Man"));
        }
        else {
            user.setGender(systemUser.getGender());
        }
        if(systemUser.getRoles() == null) {
            user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findOneByNameRole("ROLE_CLIENT"))));
        }
        else {
            user.setRoles(systemUser.getRoles());
        }
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<SystemUser> findAll() {
        return userRepository.findAll().stream()
                .map(SystemUser::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String numberPhones) throws UsernameNotFoundException {
        SystemUser user = findByNumberPhone(numberPhones);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        try{
            return new org.springframework.security.core.userdetails.User(user.getNumberPhone(), user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }
        catch (Exception e) {
            log.error("Error user authorization!", e);
            throw new BadCredentialsException("Internal error. Try again latter.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNameRole())).collect(Collectors.toList());
    }


}
