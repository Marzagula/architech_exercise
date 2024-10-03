package com.architech.user_registration.repository;

import com.architech.user_registration.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("SELECT ua FROM UserAccount ua WHERE LOWER(ua.username) = LOWER(:username)")
    UserAccount findByUsername(String username);
}
