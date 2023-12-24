package com.project.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginDTO {
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    String phoneNumber;

    @NotBlank(message = "Password is required")
    String password;
}
