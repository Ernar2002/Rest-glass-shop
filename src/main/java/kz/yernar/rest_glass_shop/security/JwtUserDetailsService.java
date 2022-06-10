package kz.yernar.rest_glass_shop.security;

import kz.yernar.rest_glass_shop.domain.Role;
import kz.yernar.rest_glass_shop.domain.User;
import kz.yernar.rest_glass_shop.security.jwt.JwtUserDetails;
import kz.yernar.rest_glass_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserService studentService;

    @Autowired
    public JwtUserDetailsService(UserService studentService) {
        this.studentService = studentService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = studentService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Username: " + email + " not found");
        }
        return new JwtUserDetails(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                grantedAuthorities(user.getRoles()));
    }

    private Set<GrantedAuthority> grantedAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }
}