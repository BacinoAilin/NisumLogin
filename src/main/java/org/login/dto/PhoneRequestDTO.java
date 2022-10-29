package org.login.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.login.model.Phone;


/**
 * The {@link PhoneRequestDTO} class represents the parameters needed for the persistence of
 * a {@link Phone} entity.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDTO {

    private Long phoneId;

    private Long number;

    private String cityCode;

    private String countryCode;
}
