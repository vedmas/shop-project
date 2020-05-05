package ru.tokarev.shop.service.user;

import lombok.extern.slf4j.Slf4j;
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

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
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
        return new SystemUser(userRepository.findById(id).orElse(new Users()));
    }

    @Override
    @Transactional
    public SystemUser findByNumberPhone(String numberPhone) {
        Users user = userRepository.findOneByNumberPhone(numberPhone);
        return (user == null) ? null : new SystemUser(user.getFirstUserName(), user.getLastUserName(), user.getNumberPhone(), user.getPassword(),
                user.getEmail(), user.getGender(), user.getRoles());
    }

    @Override
    public SystemUser findUserByNumberPhone(String numberPhone) {
        Users user = userRepository.findOneByNumberPhone(numberPhone);
            return (user == null) ? null : new SystemUser(user);
    }

    @Override
    public UserInfo authUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Users user = userRepository.findOneByNumberPhone(authentication.getName());
            if(user != null) {
                return new UserInfo(user.getId(), user.getFirstUserName(), user.getLastUserName(), user.getNumberPhone(),
                        user.getEmail(), user.getGender(), user.getRoles());
        }
        return new UserInfo();
    }

    @Override
    public boolean isUserRegistered() {
        return !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
    }

    @Override
    public boolean isAdminRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findOneByNumberPhone(authentication.getName());
        if(user != null) {
            return user.getRoles().stream().map(Roles::getNameRole).anyMatch("ROLE_ADMIN"::equals) || user.getRoles().stream().map(Roles::getNameRole).anyMatch("ROLE_MANAGER"::equals);
        }
         return false;
    }

    public boolean isManagerRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findOneByNumberPhone(authentication.getName());
        if(user != null) {
            return user.getRoles().stream().map(Roles::getNameRole).noneMatch("ROLE_ADMIN"::equals) && user.getRoles().stream().map(Roles::getNameRole).anyMatch("ROLE_MANAGER"::equals);
        }
        return false;
    }

    @Override
    public boolean existsUserByNumberPhone(String numberPhone) {
        return userRepository.existsUserByNumberPhone(numberPhone);
    }

    @Override
    @Transactional
    public void save(SystemUser systemUser) {
        Users user = systemUser.getId() != null ? userRepository.findById(systemUser.getId()).orElse(new Users()) : new Users();
        user.setNumberPhone(systemUser.getNumberPhone());
        if (systemUser.getId() == null || (systemUser.getPassword() != null && !systemUser.getPassword().trim().isEmpty())) {
            user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        }
        user.setFirstUserName(systemUser.getFirstUserName());
        user.setLastUserName(systemUser.getLastUserName());
        user.setEmail(systemUser.getEmail());
        if (systemUser.getGender() == null) {
            user.setGender(genderRepository.findOneByNameGender("Man"));
        } else {
            user.setGender(systemUser.getGender());
        }
        if (systemUser.getRoles() == null) {
            user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findOneByNameRole("ROLE_CLIENT"))));
        } else {
            user.setRoles(systemUser.getRoles());
        }
        userRepository.save(user);
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
        try {
            if (user == null) {
                throw new UsernameNotFoundException("Invalid username");
            }
            return new org.springframework.security.core.userdetails.User(user.getNumberPhone(), user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        } catch (UsernameNotFoundException u) {
            log.info(u.getMessage());
            throw new UsernameNotFoundException("Invalid username");
        } catch (Exception e) {
            log.error("Error user authorization!", e);
            throw new BadCredentialsException("Internal error. Try again latter.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNameRole())).collect(Collectors.toList());
    }
}
