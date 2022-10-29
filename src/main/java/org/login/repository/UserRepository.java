package org.login.repository;


import org.login.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The {@link @UserRepository} interface is an extension of the {@link JpaRepository},
 * configured to work with the {@link UserLogin} class.
 */
@Repository
public interface UserRepository extends JpaRepository<UserLogin, String>  {

    /**
     * Finds the {@link UserLogin} with the exact userId.
     *
     * @param userId the userId field.
     * @return {@link UserLogin} with the found {@link UserLogin}.
     */
    UserLogin findByUserId (UUID userId);

    Optional<UserLogin> findByMail (String mail);
}
