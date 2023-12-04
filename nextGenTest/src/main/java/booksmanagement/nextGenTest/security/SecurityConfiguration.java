package booksmanagement.nextGenTest.security;

import booksmanagement.nextGenTest.entities.User_Manager;
import booksmanagement.nextGenTest.services.ManagementUsers_Service;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private ManagementUsers_Service managementUsers_service;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{


        authenticationManagerBuilder.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                User_Manager userManager = managementUsers_service.showUser(username);

                Collection<GrantedAuthority> authorities = new ArrayList<>();

                userManager.getRolesManagers().forEach(rolesManager ->{
                    authorities.add(new SimpleGrantedAuthority(rolesManager.getRoleName()));
                        }
                );

                return new User(userManager.getUsername(),userManager.getPassword(),authorities);
            }
        });

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        //Ici on active la protection contre les attaques de type csrf
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        // Iici on active l'authentification stateless cad base sur les tokens
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //Ici c'est pour pouvoir acceder a la base de donnee dans h2
        httpSecurity.authorizeHttpRequests().antMatchers("/h2-console/**").permitAll();

        //Ici c'est pour pouvoir acceder a la documentation de l'API
        httpSecurity.authorizeHttpRequests().antMatchers("/v3/api-docs/**").permitAll();
       // httpSecurity.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/books/**").hasAuthority("ajout");
        //httpSecurity.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/books/**").hasAuthority("ajout");

        httpSecurity.formLogin();
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.addFilter(new FilterJwtAuthentification(authenticationManager()));
        httpSecurity.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
