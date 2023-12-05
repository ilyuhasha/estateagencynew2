package com.example.estateAgency.configuration;

import com.example.estateAgency.models.User;
import com.example.estateAgency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(login -> {
            User user = userRepository.findByLogin(login);
            String[] loginAndPassword = new String[2];
            loginAndPassword[0] = user.getLogin();
            loginAndPassword[1] = user.getPassword();
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (user.getLogin().equals("Ilya")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ILYA"));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
        }).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/product/create").permitAll()
                .antMatchers("/product/{id}").permitAll()
                .antMatchers("/product/delete/{id}").permitAll()
                .antMatchers("/?title={title}").permitAll()
                .antMatchers("/").authenticated()
//                .antMatchers("/logs").access("hasAuthority('ROLE_ILYA')")
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
        http.csrf().disable();
    }
}

