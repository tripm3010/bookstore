package com.tripm.identityservice.configuration;

import com.tripm.identityservice.constant.PredefinedRole;
import com.tripm.identityservice.entity.User;
import com.tripm.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner runner(UserRepository userRepository) {
        return args -> {
          if(userRepository.findByUsername("admin").isEmpty()){
              var roles  = new HashSet<String>();
              roles.add(PredefinedRole.ADMIN_ROLE);
              User user = User.builder()
                      .username("admin")
                      .password(passwordEncoder.encode("admin"))
//                      .role(roles)
                      .build();
              userRepository.save(user);
              log.warn("admin added");
          }

        };
    }
}
