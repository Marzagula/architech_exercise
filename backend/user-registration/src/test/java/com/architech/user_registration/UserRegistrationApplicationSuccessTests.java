package com.architech.user_registration;

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

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class UserRegistrationApplicationSuccessTests {

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
                {"username1", "passwoRd1"},
                {"username2", "pssasswordL0"},
                {"username3", "pASSWORd6"},
                {"1234567", "0009911Ll"},
        });
    }

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.registerService = new RegisterService(userAccountRepository);
    }

    @Test
    public void registrationValidationTest() {
        UserRegistrationDTO validUser = new UserRegistrationDTO(username, password);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRegistrationDTO>> violations = validator.validate(validUser);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void registrationTestNoException() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(username, password);
        when(this.userAccountRepository.findByUsername(username)).thenReturn(null);

        try {
            this.registerService.registerUser(userRegistrationDTO);
        } catch (Exception ex) {
            fail("Did not expect any exception, but got: " + ex.getClass().getSimpleName());
        }

    }

}
