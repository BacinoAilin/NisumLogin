
import org.junit.jupiter.api.Test;
import org.login.model.Phone;
import org.login.model.UserLogin;
import org.login.repository.UserRepository;
import org.login.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@SpringBootTest
@SpringJUnitConfig(UserServiceTest.UserServiceTestConfiguration.class)
@TestPropertySource(properties = { "user.password.regex=^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" })
@TestPropertySource(properties = { "user.mail.regex=^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$" })
public class UserServiceTest  {

    @Autowired
    UserService userService;

    final String uuidString = "123e4567-e89b-12d3-a456-426614174000";
    final UUID uuid =  UUID.fromString(uuidString);

    @Test
    public void createTest() {
    }

    public static class UserServiceTestConfiguration {

        @Bean
        public UserService userService() {return new UserService();}

        @Bean
        public UserRepository userRepository() {
            final UserRepository userRepository = Mockito.mock(UserRepository.class);

            final String uuidString = "123e4567-e89b-12d3-a456-426614174000";
            final UUID uuid =  UUID.fromString(uuidString);

            final Phone phone = Phone.builder()
                    .phoneId(1L)
                    .number(1234L)
                    .cityCode("7600")
                    .countryCode("767")
                    .build();

            final UserLogin user = UserLogin.builder()
                    .userId(uuid)
                    .name("save")
                    .password("1234")
                    .mail("usuario1@gmail.com")
                    .created(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .isActive(Boolean.TRUE)
                    .modified(LocalDateTime.now())
                    .token("TOKEN")
                    .phones(Set.of(phone))
                    .build();

            final UserLogin userSave = UserLogin.builder()
                    .name("save")
                    .password("1234")
                    .mail("usuario1@gmail.com")
                    .created(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .isActive(Boolean.TRUE)
                    .modified(LocalDateTime.now())
                    .token("TOKEN")
                    .phones(Set.of(phone))
                    .build();



            Mockito.when(userRepository.findByUserId(uuid)).thenReturn(user);
            Mockito.when(userRepository.save(userSave)).thenReturn(UserLogin.builder()
                    .userId(uuid)
                    .name("save")
                    .password("1234")
                    .mail("usuario1@gmail.com")
                    .created(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .isActive(Boolean.TRUE)
                    .modified(LocalDateTime.now())
                    .token("TOKEN")
                    .phones(Set.of(phone))
                    .build());

            return userRepository;
        }
    }
}
