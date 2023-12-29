package com.project.shopapp.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTOResponse {
    Long id;
    String fullName;
    String phoneNumber;
    String address;
    LocalDate dateOfBirth;
    int facebookAccountId;
    int googleAccountId;
}
