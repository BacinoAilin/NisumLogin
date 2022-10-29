package org.login.repository;

import org.login.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The {@link @PhoneRepository} interface is an extension of the {@link JpaRepository},
 * configured to work with the {@link Phone} class.
 */
@Repository
public interface PhoneRepository  extends JpaRepository<Phone, String> {

    /**
     * Finds the {@link Phone} with the exact phoneId.
     *
     * @param phoneId the phoneId field.
     * @return {@link Phone} with the found {@link Phone}.
     */
    Optional<Phone> findByPhoneId (Long phoneId);
}
