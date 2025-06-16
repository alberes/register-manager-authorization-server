package io.github.alberes.register.manager.authorization.server.services;

import io.github.alberes.register.manager.authorization.server.constants.Constants;
import io.github.alberes.register.manager.authorization.server.domains.UserAccount;
import io.github.alberes.register.manager.authorization.server.domains.UserPrincipal;
import io.github.alberes.register.manager.authorization.server.repositories.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserAccountRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = this.repository.findByEmail(username);
        if(userAccount == null){
            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException(Constants.OBJECT_NOT_FOUND);
            log.error(usernameNotFoundException.getMessage(), usernameNotFoundException);
            throw usernameNotFoundException;
        }
        userAccount.getRoles().size();
        return new UserPrincipal(userAccount);
    }
}