package io.github.alberes.register.manager.authorization.server.services;

import io.github.alberes.register.manager.authorization.server.constants.Constants;
import io.github.alberes.register.manager.authorization.server.domains.Client;
import io.github.alberes.register.manager.authorization.server.repositories.ClientRepository;
import io.github.alberes.register.manager.authorization.server.services.exceptions.DuplicateRecordException;
import io.github.alberes.register.manager.authorization.server.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository repository;

    private final PasswordEncoder encoder;

    @Transactional
    @Modifying
    public Client save(Client client){
        Client clientDB = this.repository.findByClientId(client.getClientId());
        if(clientDB != null){
            DuplicateRecordException duplicateRecordException = new DuplicateRecordException(
                    Constants.REGISTRATION_WITH_E_MAIL + client.getClientId() + Constants.HAS_ALREADY_BEEN_REGISTERED);
            log.error(duplicateRecordException.getMessage(), duplicateRecordException);
            throw duplicateRecordException;
        }
        String clientSecret = this.encoder.encode(client.getClientSecret());
        client.setClientSecret(clientSecret);
        client = this.repository.save(client);
        log.info("New client saved, id: {}", client.getId());
        return client;
    }

    public Client find(String clientId){
        Client client = this.repository.findByClientId(clientId);
        if(client == null){
            ObjectNotFoundException objectNotFoundException = new ObjectNotFoundException(
                    Constants.OBJECT_NOT_FOUND_ID + clientId + Constants.TYPE + Client.class.getName()
            );
            throw objectNotFoundException;
        }
        log.info("Client found. id: {}", client.getClientId());
        return client;
    }

    @Transactional
    @Modifying
    public void delete(String clientId){
        this.find(clientId);
        this.repository.deleteByClientId(clientId);
    }
}