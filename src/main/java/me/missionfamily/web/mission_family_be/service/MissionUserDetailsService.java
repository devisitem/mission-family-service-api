package me.missionfamily.web.mission_family_be.service;

import me.missionfamily.web.mission_family_be.domain.Account;
import me.missionfamily.web.mission_family_be.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class MissionUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return accountRepository.findOneByUserId(username)
                .map(account -> createUser(account.getUserId(), account))
                .orElseThrow(() -> new UsernameNotFoundException("There is no user by "+username));

    }

    public org.springframework.security.core.userdetails.User createUser(String username, Account account) {
        if(!account.isActivated()){
            throw new RuntimeException(username+ "-> Not Activated");
        }

        List<GrantedAuthority> grantedAuthorities = account.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(account.getUserId(),
                account.getUserPassword(),
                grantedAuthorities);
    }
}
