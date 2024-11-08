package ru.school_assistant_rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.school_assistant_rest.model.user_model.Roles;
import ru.school_assistant_rest.model.user_model.User;
import ru.school_assistant_rest.model.user_model.UserRole;
import ru.school_assistant_rest.repository.security_repository.RolesRepository;
import ru.school_assistant_rest.repository.security_repository.UserRepository;
import ru.school_assistant_rest.repository.security_repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userReposiroty;
    private final UserRoleRepository userRoleRepository;
    private final RolesRepository rolesReposiroty;



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userReposiroty.findByLogin(username).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден"));

        List<UserRole> userRole = userRoleRepository.findByUserId(user.getId());

        List<Long> rolesId = new ArrayList<>();
        for (UserRole ur: userRole) {
            rolesId.add(ur.getRolesId());
        }

        List<Roles> roles = rolesReposiroty.findAllById(rolesId);

        List<SimpleGrantedAuthority> userRoles = roles.stream().map(it -> new SimpleGrantedAuthority(it.getName())).toList();

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                userRoles);
    }

}
