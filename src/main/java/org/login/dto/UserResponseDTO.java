package org.login.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.login.model.UserLogin;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * The {@link UserResponseDTO} class represents the response of persisting
 * a {@link UserLogin} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private UUID userId;

    private String name;

    private String mail;

    private String password;

    private Set<PhoneRequestDTO> phones;

    private String token;

    private Boolean isActive;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modified;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastLogin;

    public UserResponseDTO (final UserLogin user) {

        this.userId = user.getUserId();
        this.name = user.getName();
        this.mail = user.getMail();
        this.password = user.getPassword();
        this.token = user.getToken();
        this.isActive = user.getIsActive();
        this.created = user.getCreated();
        this.modified = user.getModified();
        this.lastLogin = user.getLastLogin();
    }


}
