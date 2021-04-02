package me.missionfamily.web.mission_family_be.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebMvcConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/home").permitAll()
                .anyRequest().authenticated()
                .and()
             .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
             .logout()
                .permitAll();
    }


    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {


        return super.userDetailsServiceBean();
    }
}
