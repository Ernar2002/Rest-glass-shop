package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Role;
import kz.yernar.rest_glass_shop.repository.RoleRepository;
import kz.yernar.rest_glass_shop.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl (
            RoleRepository roleRepository
    ) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void update(Long id, Role role) {
        Role roleFromDb = getById(id);

        roleFromDb.setName(role.getName());

        save(roleFromDb);
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(getById(id));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public boolean isExist(String name) {
        if(findByName(name) != null){
            return true;
        } else return false;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
