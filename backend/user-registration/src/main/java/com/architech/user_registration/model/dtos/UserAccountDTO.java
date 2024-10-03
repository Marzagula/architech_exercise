package com.architech.user_registration.model.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserAccountDTO(
        Long id,
        String username,
        LocalDate registerDate,
        LocalDateTime lastLoginDate
) {
}
