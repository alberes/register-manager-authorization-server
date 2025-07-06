package io.github.alberes.register.manager.authorization.server.repositories;

import io.github.alberes.register.manager.authorization.server.domains.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    public UserAccount findByEmail(String email);

}
