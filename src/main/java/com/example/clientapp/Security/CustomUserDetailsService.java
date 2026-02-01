package com.example.clientapp.Security;

import com.example.clientapp.model.Boss;
import com.example.clientapp.repository.BossRepository;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final BossRepository bossRepository;

    public CustomUserDetailsService(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        System.out.println("LOGIN ATTEMPT: " + username);

        Boss boss = bossRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Boss not found"));

        return User.builder()
                .username(boss.getUsername())
                .password(boss.getPassword()) // plain text
                .roles("BOSS")
                .build();
    }
}
