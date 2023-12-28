package com.project.shopapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTORequest {
    @JsonProperty("fullname")
    @NotBlank(message = "Full name is required")
    String fullName;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    String phoneNumber;

    String address;

    @NotBlank(message = "Password is required")
    String password;

    @JsonProperty("retype_password")
    @NotBlank(message = "Retype Password is required")
    String retypePassword;

    @JsonProperty("date_of_birth")
    Date dateOfBirth;

    @JsonProperty("facebook_account_id")
    Integer facebookAccountId;

    @JsonProperty("google_account_id")
    Integer googleAccountId;

    @JsonProperty("role_id")
    @NotNull(message = "Role id is required")
    private Long roleId;
}
