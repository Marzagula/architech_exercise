package com.architech.user_registration;

import com.architech.user_registration.exception.DuplicateException;
import com.architech.user_registration.model.UserAccount;
import com.architech.user_registration.model.dtos.UserRegistrationDTO;
import com.architech.user_registration.repository.UserAccountRepository;
import com.architech.user_registration.service.RegisterService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class UserRegistrationApplicationFailsTests {

    @Parameterized.Parameter(0)
    public String username;
    @Parameterized.Parameter(1)
    public String password;

    @Mock
    UserAccountRepository userAccountRepository;

    RegisterService registerService;

    @Parameterized.Parameters(name = "{index}: Test with username: {0}, password: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"user", "passwoRd1"},
                {"username1", "pssassword"},
                {"username3", "pASSWORd"}
        });
    }

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.registerService = new RegisterService(userAccountRepository);
    }

    @Test
    public void registrationValidationTest() {
        UserRegistrationDTO invalidUser = new UserRegistrationDTO(username, password);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRegistrationDTO>> violations = validator.validate(invalidUser);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void registrationTestDuplicateUser() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(username, password);


        when(this.userAccountRepository.findByUsername(username)).thenReturn(getUserAccountFromUserRegistrationDTO(userRegistrationDTO));
        DuplicateException thrown = assertThrows(DuplicateException.class, () -> {
            this.registerService.registerUser(userRegistrationDTO);
        });

        assertEquals("User with name \"" + userRegistrationDTO.username() + "\" already exists.", thrown.getMessage());
    }

    private UserAccount getUserAccountFromUserRegistrationDTO(UserRegistrationDTO userRegistrationDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userRegistrationDTO.username());
        userAccount.setPassword(userRegistrationDTO.password());
        userAccount.setRegisterDate(LocalDate.now());
        return userAccount;
    }

}
