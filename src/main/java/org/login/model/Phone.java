package org.login.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The {@link Phone} class is used to create phone number a User needs.
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    private Long phoneId;

    private Long number;

    private String cityCode;

    private String countryCode;

    @ManyToOne()
    @JoinColumn(name = "userId")
    @ToString.Exclude
    @JsonBackReference
    private UserLogin user;

}
