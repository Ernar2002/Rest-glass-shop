package kz.yernar.rest_glass_shop.config;

import kz.yernar.rest_glass_shop.security.jwt.JwtConfigurer;
import kz.yernar.rest_glass_shop.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends WebSecurityConfigurerAdapter {
    private static final String AUTH_ENDPOINT = "/api/auth/**";

    private final JwtUtil jwtUtil;

    @Autowired
    public AppConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                .anyRequest().hasAuthority("USER")
                .and()
                .formLogin()
                .loginPage("/api/auth/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .apply(new JwtConfigurer(jwtUtil));
        http.headers().cacheControl();
    }
}
