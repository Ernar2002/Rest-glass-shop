package kz.yernar.rest_glass_shop.service;

import kz.yernar.rest_glass_shop.domain.Role;

public interface RoleService extends BaseService<Role> {
    boolean isExist(String name);
    Role findByName(String name);
}
