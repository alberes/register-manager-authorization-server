package io.github.alberes.register.manager.authorization.server.repositories;

import io.github.alberes.register.manager.authorization.server.domains.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    public Client findByClientId(String clientId);

}
