package com.tripm.profileservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileResponse {

    String id;
    String email;
    String username;
    String avatar;
    String firstname;
    String lastname;
    LocalDate dob;
    String city;
}
