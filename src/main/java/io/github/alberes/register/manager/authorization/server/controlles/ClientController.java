package io.github.alberes.register.manager.authorization.server.controlles;

import io.github.alberes.register.manager.authorization.server.constants.Constants;
import io.github.alberes.register.manager.authorization.server.controlles.dto.ClientDto;
import io.github.alberes.register.manager.authorization.server.controlles.exceptions.dto.StandardErrorDto;
import io.github.alberes.register.manager.authorization.server.controlles.mappers.ClientMapper;
import io.github.alberes.register.manager.authorization.server.domains.Client;
import io.github.alberes.register.manager.authorization.server.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController implements GenericController{

    private final ClientService service;

    private final ClientMapper mapper;

    @PostMapping
    //@PreAuthorize(Constants.HAS_ROLE_ADMIN)
    @Operation(summary = "Save client.", description = "Save client in database.",
            operationId = "saveClient")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Saved with success."),
            @ApiResponse(responseCode = "400", description = "Validation error.",
                    content = @Content(schema = @Schema(implementation = StandardErrorDto.class))),
            @ApiResponse(responseCode = "403", description = Constants.UNAUTHORIZED_MESSAGE,
                    content = @Content(schema = @Schema(implementation = StandardErrorDto.class))),
            @ApiResponse(responseCode = "409", description = "There is a client with clientId.",
                    content = @Content(schema = @Schema(implementation = StandardErrorDto.class)))
    })
    public ResponseEntity<Void> save(@RequestBody @Valid ClientDto dto){
        Client client = this.mapper.toEntity(dto);
        client = this.service.save(client);
        return ResponseEntity
                .created(this.createURI("/{id}", client.getId().toString()))
                .build();
    }

    @DeleteMapping("/{clientId}")
    //@PreAuthorize(Constants.HAS_ROLE_ADMIN_USER)
    @Operation(summary = "Delete client.", description = "Delete with success.",
            operationId = "deleteClient")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Delete client in database."),
            @ApiResponse(responseCode = "403", description = Constants.UNAUTHORIZED_MESSAGE,
                    content = @Content(schema = @Schema(implementation = StandardErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "Could not find client in database.",
                    content = @Content(schema = @Schema(implementation = StandardErrorDto.class)))
    })
    public ResponseEntity<Void> delete(@PathVariable String clientId){
        this.service.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}