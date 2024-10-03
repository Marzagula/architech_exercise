package com.architech.user_registration;

import com.architech.user_registration.model.dtos.UserAccountDTO;
import com.architech.user_registration.model.dtos.UserRegistrationDTO;
import com.architech.user_registration.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class Controller {
    private final RegisterService registerService;

    public Controller(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    ResponseEntity<UserAccountDTO> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.ok(registerService.registerUser(userRegistrationDTO));
    }

    @GetMapping
    ResponseEntity<Page<UserAccountDTO>> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(registerService.findAllUsers(PageRequest.of(page,size)));
    }
}
