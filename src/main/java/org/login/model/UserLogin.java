package org.login.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * The {@link UserLogin} class is used to create the characteristic a user needs to check in.
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    @Id
    @GeneratedValue(generator = "userId")
    @GenericGenerator(name = "userId", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(name = "userId", columnDefinition = "VARCHAR(255)")
    private UUID userId;

    private String name;

    private String mail;

    private String password;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonManagedReference
    private Set<Phone> phones;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    private String token;

    private Boolean isActive;

}
