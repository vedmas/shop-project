package ru.tokarev.shop.service.role;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.controller.repr.RoleRepr;
import ru.tokarev.shop.repository.RoleRepository;
import ru.tokarev.shop.repository.entity.Roles;

import java.io.Serializable;
import java.util.List;

@Service("roleService")
@Slf4j
public class RoleServiceImpl implements RoleService, Serializable {

    private static final long serialVersionUID = -5255259920835146847L;

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Roles get(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void saveRoles(RoleRepr roleRepr) {
        Roles role = new Roles();
        role.setId(roleRepr.getId());
        role.setNameRole(roleRepr.getNameRole());
        roleRepository.save(role);
        log.info("Role id {} name {} save or update", role.getId(), role.getNameRole());
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void delete(Roles roles) {
        roleRepository.delete(roles);
    }

    @Override
    public Roles findOneByNameRole(String nameRole) {
        return roleRepository.findOneByNameRole(nameRole);
    }
}
