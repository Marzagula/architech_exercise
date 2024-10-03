package com.architech.user_registration.model.dtos;

import jakarta.validation.constraints.Pattern;

public record UserRegistrationDTO(
        @Pattern(regexp = "^[a-zA-Z0-9]{5,}$",
                message = "Username must be at least 5 characters long and can only contain alphanumeric characters.")
        String username,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d\\S]{8,}$",
                message = "Password must be at least 8 characters long and must contain at least 1 number, 1 uppercase, and 1 lowercase character.")
        String password
) {
}
