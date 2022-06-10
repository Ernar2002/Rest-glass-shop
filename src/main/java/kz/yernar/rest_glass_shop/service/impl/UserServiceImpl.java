package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Role;
import kz.yernar.rest_glass_shop.domain.User;
import kz.yernar.rest_glass_shop.domain.enums.EActive;
import kz.yernar.rest_glass_shop.repository.RoleRepository;
import kz.yernar.rest_glass_shop.repository.UserRepository;
import kz.yernar.rest_glass_shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));

        if(user.getRoles() == null) {
            user.setRoles(roles);
        }

        if (user.getActive() == null) {
            user.setActive(EActive.ACTIVE);
        }

        if(!user.getPassword().startsWith("$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if(user.getCreated() == null) {
            Date date = new Date();
            user.setCreated(date);
        }
        userRepository.save(user);

        log.info("In UserServiceImpl method save: {} successfully saved", user);
    }

    @Override
    public void update(Long id, User user) {

    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(getById(id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
