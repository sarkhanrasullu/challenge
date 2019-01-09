package com.outfittery.service.impl;

import com.outfittery.entity.UserGroup;
import com.outfittery.entity.GroupRole;
import com.outfittery.repository.UserRepository;
import com.outfittery.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        UserGroup group = user.getGroupId();
        List<GroupRole> groupRoles = group.getGroupRoleList();
        groupRoles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleId().getName()));
        });

        UserBuilder builder = org.springframework.security.core.userdetails.User.builder();
        builder
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .disabled(user.isBlocked());
        UserDetails userDetails = builder.build();
      
        return userDetails;
    }
}
