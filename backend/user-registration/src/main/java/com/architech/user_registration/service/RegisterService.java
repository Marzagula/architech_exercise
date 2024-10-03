package com.architech.user_registration.service;

import com.architech.user_registration.exception.DuplicateException;
import com.architech.user_registration.model.UserAccount;
import com.architech.user_registration.model.dtos.UserAccountDTO;
import com.architech.user_registration.model.dtos.UserRegistrationDTO;
import com.architech.user_registration.repository.UserAccountRepository;
import com.architech.user_registration.utils.UserAccountMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;

@Service
public class RegisterService {

    private final UserAccountRepository userAccountRepository;

    public RegisterService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        UserAccount test = userAccountRepository.findByUsername(userRegistrationDTO.username().toLowerCase(Locale.ROOT));
        if (test != null) {
            throw new DuplicateException("User with name \"" + userRegistrationDTO.username() + "\" already exists.");
        }
        UserAccount newUserAccount = new UserAccount();
        newUserAccount.setPassword(userRegistrationDTO.password());
        newUserAccount.setUsername(userRegistrationDTO.username());
        newUserAccount.setRegisterDate(LocalDate.now());
        return UserAccountMapper.INSTANCE.toUserDTO(userAccountRepository.save(newUserAccount));
    }

    public Page<UserAccountDTO> findAllUsers(PageRequest pageable) {
        return userAccountRepository.findAll(pageable).map(UserAccountMapper.INSTANCE::toUserDTO);
    }
}
