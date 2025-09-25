package br.com.fiap.apisecurity.controller;

import br.com.fiap.apisecurity.dto.AuthDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AuthDTO authDTO) {
        var userPwd = new UsernamePasswordAuthenticationToken(
                authDTO.username(),
                authDTO.password()
        );

        authenticationManager.authenticate(userPwd);
        return ResponseEntity.ok().build();
    }
}
