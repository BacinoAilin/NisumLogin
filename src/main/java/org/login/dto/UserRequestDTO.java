package org.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.login.model.Phone;
import org.login.model.UserLogin;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The {@link UserRequestDTO} class represents the parameters needed for the persistence of
 * a {@link UserLogin} entity.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String name;

    private String mail;

    private String password;

    private Set<PhoneRequestDTO> phones;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    private String token;

    private Boolean isActive;

    public UserLogin build() {

        Set<Phone> phoneList = this.getPhones().stream()
                .map(phoneDto -> Phone.builder()
                        .number(phoneDto.getNumber())
                        .countryCode(phoneDto.getCountryCode())
                        .cityCode(phoneDto.getCityCode())
                        .build())
                .collect(Collectors.toSet());


        UserLogin user = UserLogin.builder()
                .name(this.name)
                .mail(this.mail)
                .password(this.password)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .phones(phoneList)
                .build();




        user.getPhones().forEach(phone -> phone.setUser(user));

        return user;

    }
}
